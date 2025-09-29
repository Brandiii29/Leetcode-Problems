import java.util.Arrays;

class MyCircularQueue {
    // Array to store the queue elements
    private final int[] data;
    
    // Maximum capacity of the queue (k)
    private final int capacity;
    
    // Index of the front element
    private int front;
    
    // Current number of elements in the queue
    private int size;
    
    /**
     * Initializes the object with the size of the queue to be k.
     * @param k The maximum size of the circular queue.
     */
    public MyCircularQueue(int k) {
        // We use an array of size k to fully utilize the capacity.
        this.data = new int[k];
        this.capacity = k;
        this.front = 0;
        this.size = 0;
    }
    
    /**
     * Inserts an element into the circular queue. 
     * @param value The value to insert.
     * @return true if the operation is successful, false if the queue is full.
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        
        // Calculate the index where the new element should be placed.
        // The insertion point (rear) is (front + size) % capacity.
        int rearIndex = (front + size) % capacity;
        data[rearIndex] = value;
        
        // Increase the size of the queue.
        size++;
        return true;
    }
    
    /**
     * Deletes an element from the circular queue (removes the front element).
     * @return true if the operation is successful, false if the queue is empty.
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        
        // Move the front pointer to the next position circularly.
        // We don't need to physically remove the old element; it will be overwritten on the next enQueue.
        front = (front + 1) % capacity;
        
        // Decrease the size of the queue.
        size--;
        return true;
    }
    
    /**
     * Gets the front item from the queue.
     * @return The front item, or -1 if the queue is empty.
     */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        // The front element is always at the 'front' index.
        return data[front];
    }
    
    /**
     * Gets the last item from the queue (the rear element).
     * @return The rear item, or -1 if the queue is empty.
     */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        
        // The rear element is at the index just before the next insertion point.
        // Next insertion point = (front + size) % capacity.
        // Index of the last element = (front + size - 1) % capacity.
        // Since we are guaranteed size > 0 here, (size - 1) is safe.
        int rearIndex = (front + size - 1) % capacity;
        return data[rearIndex];
    }
    
    /**
     * Checks whether the circular queue is empty or not.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Checks whether the circular queue is full or not.
     * @return true if the queue is full, false otherwise.
     */
    public boolean isFull() {
        return size == capacity;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */