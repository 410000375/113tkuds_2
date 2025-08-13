import java.util.*;

public class MovingAverageStream {
    private int size;
    private Deque<Integer> window;
    private long sum;

    // 中位數結構
    private PriorityQueue<Integer> left;  // max heap
    private PriorityQueue<Integer> right; // min heap
    private Map<Integer, Integer> delayed; // 延遲刪除計數

    // 極值結構
    private Deque<Integer> minDeque;
    private Deque<Integer> maxDeque;

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new ArrayDeque<>();
        this.sum = 0;

        this.left = new PriorityQueue<>(Collections.reverseOrder());
        this.right = new PriorityQueue<>();
        this.delayed = new HashMap<>();

        this.minDeque = new ArrayDeque<>();
        this.maxDeque = new ArrayDeque<>();
    }

    public double next(int val) {
        if (window.size() == size) {
            int old = window.pollFirst();
            sum -= old;
            markForRemoval(old);
            if (!minDeque.isEmpty() && minDeque.peekFirst() == old) minDeque.pollFirst();
            if (!maxDeque.isEmpty() && maxDeque.peekFirst() == old) maxDeque.pollFirst();
        }

        window.offerLast(val);
        sum += val;

        addNum(val);
        while (!minDeque.isEmpty() && minDeque.peekLast() > val) minDeque.pollLast();
        minDeque.offerLast(val);

        while (!maxDeque.isEmpty() && maxDeque.peekLast() < val) maxDeque.pollLast();
        maxDeque.offerLast(val);

        return sum * 1.0 / window.size();
    }

    public double getMedian() {
        prune(left);
        prune(right);
        if (left.size() == right.size()) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return left.size() > right.size() ? left.peek() : right.peek();
        }
    }

    public int getMin() {
        return minDeque.peekFirst();
    }

    public int getMax() {
        return maxDeque.peekFirst();
    }

    // --- 中位數輔助方法 ---
    private void addNum(int num) {
        if (left.isEmpty() || num <= left.peek()) {
            left.offer(num);
        } else {
            right.offer(num);
        }
        balanceHeaps();
    }

    private void markForRemoval(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (num <= left.peek()) {
            // 在左堆
            if (left.peek() == num) prune(left);
        } else {
            if (right.peek() == num) prune(right);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        if (left.size() > right.size() + 1) {
            right.offer(left.poll());
            prune(left);
        } else if (right.size() > left.size()) {
            left.offer(right.poll());
            prune(right);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.containsKey(heap.peek())) {
            int num = heap.poll();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) delayed.remove(num);
        }
    }
}
