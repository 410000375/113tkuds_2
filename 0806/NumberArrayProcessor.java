import java.util.*;

public class NumberArrayProcessor {

    public static void main(String[] args) {
        int[] array1 = {1, 2, 2, 3, 4, 4, 5};
        int[] array2 = {2, 4, 6, 8};

        System.out.println("原始陣列 array1： " + Arrays.toString(array1));
        System.out.println("移除重複： " + Arrays.toString(removeDuplicates(array1)));

        System.out.println("合併 array1 和 array2： " + Arrays.toString(mergeSortedArrays(array1, array2)));

        System.out.println("array1 中出現頻率最高的元素： " + findMostFrequent(array1));

        int[][] splitResult = splitArrayEqually(array1);
        System.out.println("分割子陣列 1：" + Arrays.toString(splitResult[0]));
        System.out.println("分割子陣列 2：" + Arrays.toString(splitResult[1]));
    }

    // 移除陣列中的重複元素
    public static int[] removeDuplicates(int[] array) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : array) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        return result;
    }

    // 合併兩個已排序的陣列
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            merged[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            merged[k++] = arr2[j++];
        }

        return merged;
    }

    // 找出陣列中出現頻率最高的元素
    public static int findMostFrequent(int[] array) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : array) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxCount = 0;
        int mostFrequent = array[0];
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }

        return mostFrequent;
    }

    // 將陣列分割成兩個相等（或近似相等）大小的子陣列
    public static int[][] splitArrayEqually(int[] array) {
        int mid = array.length / 2;
        int[] part1 = Arrays.copyOfRange(array, 0, mid);
        int[] part2 = Arrays.copyOfRange(array, mid, array.length);
        return new int[][] { part1, part2 };
    }
}
