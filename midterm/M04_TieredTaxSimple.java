import java.util.*;

public class M04_TieredTaxSimple {
    // 計算單筆收入的稅額
    public static int calcTax(int income) {
        int tax = 0;
        
        // 區間逐段累加
        if (income > 1_000_000) {
            tax += (income - 1_000_000) * 30 / 100;
            income = 1_000_000;
        }
        if (income > 500_000) {
            tax += (income - 500_000) * 20 / 100;
            income = 500_000;
        }
        if (income > 120_000) {
            tax += (income - 120_000) * 12 / 100;
            income = 120_000;
        }
        if (income > 0) {
            tax += income * 5 / 100;
        }

        return tax;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        
        int totalTax = 0;
        for (int i = 0; i < n; i++) {
            int income = Integer.parseInt(sc.nextLine());
            int tax = calcTax(income);
            System.out.println("Tax: " + tax);
            totalTax += tax;
        }
        
        // 平均取整數
        int avg = totalTax / n;
        System.out.println("Average: " + avg);
    }
}

/*
時間複雜度：
- 單筆收入計算稅額：O(1)，因為最多就檢查 4 個區間。
- 輸入 n 筆資料：O(n)。
- 總時間複雜度：O(n)。
- 空間複雜度：O(1)，只用了常數變數。
*/
