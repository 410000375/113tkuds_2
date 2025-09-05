import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int val = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        sc.close();

        int newLength = removeElement(arr, val);

        System.out.println(newLength);
        for (int i = 0; i < newLength; i++) {
            System.out.print(arr[i]);
            if (i < newLength - 1) System.out.print(" ");
        }
        System.out.println();
    }

    public static int removeElement(int[] nums, int val) {
        int write = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[write++] = nums[i];
            }
        }
        return write;
    }
}
