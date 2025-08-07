import java.util.*;

public class RecursiveTreePreview {

    // 1. 模擬檔案系統中的資料夾
    static class FileNode {
        String name;
        boolean isFile;
        List<FileNode> children;

        FileNode(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            this.children = new ArrayList<>();
        }

        void addChild(FileNode node) {
            children.add(node);
        }
    }

    // 遞迴計算總檔案數
    public static int countFiles(FileNode node) {
        if (node.isFile) return 1;
        int count = 0;
        for (FileNode child : node.children) {
            count += countFiles(child);
        }
        return count;
    }

    // 2. 遞迴列印多層選單結構
    static class MenuItem {
        String title;
        List<MenuItem> children;

        MenuItem(String title) {
            this.title = title;
            this.children = new ArrayList<>();
        }

        void addChild(MenuItem child) {
            children.add(child);
        }
    }

    public static void printMenu(MenuItem item, int depth) {
        System.out.println("  ".repeat(depth) + "- " + item.title);
        for (MenuItem child : item.children) {
            printMenu(child, depth + 1);
        }
    }

    // 3. 遞迴展平巢狀陣列（List of Object，可是內層也可能是 List）
    public static List<Object> flatten(List<Object> nestedList) {
        List<Object> result = new ArrayList<>();
        for (Object elem : nestedList) {
            if (elem instanceof List) {
                result.addAll(flatten((List<Object>) elem));
            } else {
                result.add(elem);
            }
        }
        return result;
    }

    // 4. 遞迴計算巢狀清單最大深度
    public static int maxDepth(List<Object> nestedList) {
        int max = 1;
        for (Object elem : nestedList) {
            if (elem instanceof List) {
                max = Math.max(max, 1 + maxDepth((List<Object>) elem));
            }
        }
        return max;
    }

    // 測試主程式
    public static void main(String[] args) {
        // 測試 1：檔案總數
        FileNode root = new FileNode("root", false);
        FileNode file1 = new FileNode("file1.txt", true);
        FileNode folder1 = new FileNode("folder1", false);
        FileNode file2 = new FileNode("file2.txt", true);
        FileNode folder2 = new FileNode("folder2", false);
        FileNode file3 = new FileNode("file3.txt", true);

        folder1.addChild(file2);
        folder2.addChild(file3);
        root.addChild(file1);
        root.addChild(folder1);
        root.addChild(folder2);

        System.out.println("📁 總檔案數: " + countFiles(root));

        // 測試 2：列印選單
        MenuItem main = new MenuItem("主選單");
        MenuItem sub1 = new MenuItem("子選單 1");
        MenuItem sub2 = new MenuItem("子選單 2");
        MenuItem sub11 = new MenuItem("子選單 1-1");

        sub1.addChild(sub11);
        main.addChild(sub1);
        main.addChild(sub2);

        System.out.println("\n📋 多層選單結構:");
        printMenu(main, 0);

        // 測試 3：展平巢狀陣列
        List<Object> nestedList = Arrays.asList(1, Arrays.asList(2, 3), Arrays.asList(Arrays.asList(4, 5), 6), 7);
        System.out.println("\n🔁 展平結果:");
        System.out.println(flatten(nestedList));

        // 測試 4：最大深度
        System.out.println("\n📏 最大深度: " + maxDepth(nestedList));
    }
}
