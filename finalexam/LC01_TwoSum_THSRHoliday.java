import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 讀取 n 與 target
        int n = sc.nextInt();
        long target = sc.nextLong(); // target 與座位數可能很大，要用 long
        
        long[] seats = new long[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextLong();
        }
        
        // HashMap<需要的數, 索引>
        Map<Long, Integer> map = new HashMap<>();
        
        int ans1 = -1, ans2 = -1;
        
        for (int i = 0; i < n; i++) {
            long x = seats[i];
            
            // 檢查 x 是否有人在等
            if (map.containsKey(x)) {
                ans1 = map.get(x);
                ans2 = i;
                break;
            }
            
            // 記錄「還需要 target - x」
            long need = target - x;
            map.put(need, i);
        }
        
        if (ans1 == -1) {
            System.out.println("-1 -1");
        } else {
            System.out.println(ans1 + " " + ans2);
        }
    }
}
