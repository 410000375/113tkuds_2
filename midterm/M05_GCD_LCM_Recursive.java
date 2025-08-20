import java.util.*;

public class M05_GCD_LCM_Recursive {

    // 遞迴實作 GCD（輾轉相除法）
    private static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // LCM = a / gcd(a,b) * b，避免溢位
    private static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long g = gcd(a, b);
        long l = lcm(a, b);

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }
}

/*
時間複雜度:
- GCD 使用遞迴輾轉相除法，時間複雜度為 O(log(min(a,b)))。
- LCM 只需一次除法與乘法，O(1)。
- 總時間複雜度：O(log(min(a,b)))。
- 空間複雜度：O(log(min(a,b)))（來自遞迴堆疊深度）。
*/
