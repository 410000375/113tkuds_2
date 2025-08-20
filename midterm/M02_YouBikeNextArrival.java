
import java.util.*;

public class M02_YouBikeNextArrival {
    // 把 HH:mm 轉成「自 00:00 起的分鐘數」
    private static int toMinutes(String time) {
        String[] parts = time.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }

    // 把分鐘數轉回 HH:mm（補零）
    private static String toHHMM(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = Integer.parseInt(sc.nextLine().trim());
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = toMinutes(sc.nextLine().trim());
        }
        int query = toMinutes(sc.nextLine().trim());

        // 二分搜尋：找第一個 > query 的時間
        int left = 0, right = n - 1, ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (times[mid] > query) {
                ans = mid; 
                right = mid - 1; // 還要看看有沒有更前面的
            } else {
                left = mid + 1;
            }
        }

        if (ans == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHMM(times[ans]));
        }
    }
}
