import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {
    public static int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }
    private static void mergeSort(int[] nums, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);
            merge(nums, left, mid, right);
        }
    }
    private static void merge(int[] nums, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = nums[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = nums[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                nums[k] = L[i];
                i++;
            } else {
                nums[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            nums[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            nums[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter array elements separated by spaces:");
        String inputLine = scanner.nextLine();
        
        try {
            int[] nums = Arrays.stream(inputLine.split(" "))
                              .mapToInt(Integer::parseInt)
                              .toArray();

            int[] sortedNums = sortArray(nums);
            System.out.println("Sorted array: " + Arrays.toString(sortedNums));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid sequence of integers.");
        } finally {
            scanner.close();
        }
    }
}