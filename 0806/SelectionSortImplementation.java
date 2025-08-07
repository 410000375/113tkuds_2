public class SelectionSortImplementation {

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;

        System.out.println("Selection Sort Process:");
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }

            printArray(arr, i + 1);
        }

        System.out.println("Selection Sort - Total comparisons: " + comparisons);
        System.out.println("Selection Sort - Total swaps: " + swaps);
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;

        System.out.println("Bubble Sort Process:");
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            printArray(arr, i + 1);
            if (!swapped) break;
        }

        System.out.println("Bubble Sort - Total comparisons: " + comparisons);
        System.out.println("Bubble Sort - Total swaps: " + swaps);
    }

    public static void printArray(int[] arr, int round) {
        System.out.print("Round " + round + ": ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] original = {29, 10, 14, 37, 13};
        
        int[] arr1 = original.clone();
        int[] arr2 = original.clone();

        selectionSort(arr1);
        System.out.println();

        bubbleSort(arr2);
    }
}
