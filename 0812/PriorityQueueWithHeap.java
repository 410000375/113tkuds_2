import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

class Task {
    String name;
    int priority;
    long timestamp; // 用來確保同優先級時按加入先後排序

    public Task(String name, int priority, long timestamp) {
        this.name = name;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "[" + name + ", 優先級=" + priority + "]";
    }
}

public class PriorityQueueWithHeap {
    private PriorityQueue<Task> heap;
    private long timeCounter; // 用來記錄插入順序
    private List<Task> allTasks; // 用來支援 changePriority 時搜尋

    public PriorityQueueWithHeap() {
        // Comparator：先比 priority，大的先；如果相同，比 timestamp，小的先
        heap = new PriorityQueue<>(new Comparator<Task>() {
            public int compare(Task t1, Task t2) {
                if (t2.priority != t1.priority) {
                    return t2.priority - t1.priority;
                }
                return Long.compare(t1.timestamp, t2.timestamp);
            }
        });
        timeCounter = 0;
        allTasks = new ArrayList<>();
    }

    public void addTask(String name, int priority) {
        Task task = new Task(name, priority, timeCounter++);
        heap.offer(task);
        allTasks.add(task);
    }

    public Task executeNext() {
        return heap.poll();
    }

    public Task peek() {
        return heap.peek();
    }

    public boolean changePriority(String name, int newPriority) {
        for (Task t : allTasks) {
            if (t.name.equals(name)) {
                // 修改任務優先級
                t.priority = newPriority;
                // 重新建立 heap（Java PriorityQueue 無法直接更新元素優先級）
                rebuildHeap();
                return true;
            }
        }
        return false;
    }

    private void rebuildHeap() {
        heap.clear();
        heap.addAll(allTasks);
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();

        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println("下一個任務: " + pq.peek()); // 緊急修復

        System.out.println("執行: " + pq.executeNext()); // 緊急修復
        System.out.println("執行: " + pq.executeNext()); // 更新
        System.out.println("執行: " + pq.executeNext()); // 備份

        // 測試修改優先級
        pq.addTask("掃描", 2);
        pq.addTask("清理", 4);
        pq.changePriority("掃描", 6);

        System.out.println("修改後下一個任務: " + pq.peek()); // 掃描（優先級6）
    }
}
