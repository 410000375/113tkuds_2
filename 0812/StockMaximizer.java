import java.util.*;

public class StockMaximizer {
    public static int maxProfit(int[] prices, int K) {
        if (prices == null || prices.length < 2 || K <= 0) return 0;

        // 儲存所有可能的利潤（大到小）
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int buy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > buy) {
                // 上漲段落：找到賣出利潤
                maxHeap.offer(prices[i] - buy);
                buy = prices[i]; // 更新買入價
            } else {
                // 價格下降，更新買入價
                buy = prices[i];
            }
        }

        // 選擇最大 K 筆交易
        int profit = 0;
        while (K > 0 && !maxHeap.isEmpty()) {
            profit += maxHeap.poll();
            K--;
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices1 = {2, 4, 1};
        System.out.println(maxProfit(prices1, 2)); // 2

        int[] prices2 = {3, 2, 6, 5, 0, 3};
        System.out.println(maxProfit(prices2, 2)); // 7

        int[] prices3 = {1, 2, 3, 4, 5};
        System.out.println(maxProfit(prices3, 2)); // 4
    }
}
