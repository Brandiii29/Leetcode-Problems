class CustomStack {

    // The primary array to store stack elements
    private final int[] stack;
    
    // The maximum capacity of the stack
    private final int maxSize;
    
    // The current index of the top element (-1 when empty)
    private int top;
    
    // Array for lazy propagation of increment operations.
    // increment[i] stores the amount to be added to stack[i] upon popping.
    private final int[] increment;

    /**
     * Initializes the object with maxSize which is the maximum number of elements in the stack.
     */
    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
        this.increment = new int[maxSize]; // Initialize with all zeros
        this.top = -1;
    }
    
    /**
     * Adds x to the top of the stack if the stack has not reached the maxSize.
     */
    public void push(int x) {
        if (top + 1 < maxSize) {
            top++;
            stack[top] = x;
            // A newly pushed element has no pending increment
            increment[top] = 0; 
        }
        // If the stack is full, do nothing.
    }
    
    /**
     * Pops and returns the top of the stack or -1 if the stack is empty.
     */
    public int pop() {
        if (top == -1) {
            return -1; // Stack is empty
        }
        
        int i = top;
        // 1. Get the increment value pending for the current top element
        int incValue = increment[i];
        
        // 2. Calculate the final value to return (actual value + pending increment)
        int result = stack[i] + incValue;
        
        // 3. Propagate the increment value to the element below (lazy propagation)
        // This ensures the increment is applied to all elements it was meant for.
        if (i > 0) {
            increment[i - 1] += incValue;
        }
        
        // 4. Decrease the size (pop)
        top--;
        
        return result;
    }
    
    /**
     * Increments the bottom k elements of the stack by val. 
     * If there are less than k elements in the stack, increment all the elements in the stack.
     */
    public void inc(int k, int val) {
        if (top == -1) {
            return; // Stack is empty, nothing to increment
        }
        
        // Determine the index of the element that represents the upper boundary of the increment range.
        // The indices range from 0 (bottom) up to 'top'.
        // We want to affect up to the k-th element from the bottom, which is at index k-1.
        // We must ensure the index does not exceed the current top.
        int effectiveIndex = Math.min(k - 1, top);
        
        // Apply the increment ONLY at the boundary of the affected range.
        // This value will be lazily propagated down to all elements below this index when they are popped.
        increment[effectiveIndex] += val;
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */