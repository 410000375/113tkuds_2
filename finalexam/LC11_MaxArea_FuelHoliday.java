import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }
        System.out.println(maxArea(heights));
    }

    public static long maxArea(int[] heights) {
        int l = 0, r = heights.length - 1;
        long max = 0;

        while (l < r) {
            long width = r - l;
            long height = Math.min(heights[l], heights[r]);
            max = Math.max(max, width * height);

            if (heights[l] < heights[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }
}
