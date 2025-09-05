import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        double[] A = new double[n];
        double[] B = new double[m];
        
        for (int i = 0; i < n; i++) A[i] = sc.nextDouble();
        for (int j = 0; j < m; j++) B[j] = sc.nextDouble();
        
        // 保證 A 為短的
        if (n > m) {
            double[] tmp = A; A = B; B = tmp;
            int t = n; n = m; m = t;
        }
        
        int total = n + m;
        int half = (total + 1) / 2;
        
        int left = 0, right = n;
        double median = 0.0;
        
        while (left <= right) {
            int i = (left + right) / 2; // A 的切割
            int j = half - i;           // B 的切割
            
            double Aleft  = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft  = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];
            
            if (Aleft <= Bright && Bleft <= Aright) {
                // 找到正確切割
                if (total % 2 == 1) {
                    median = Math.max(Aleft, Bleft);
                } else {
                    median = (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
                break;
            } else if (Aleft > Bright) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }
        
        // 輸出保留 1 位小數
        System.out.printf("%.1f\n", median);
    }
}
