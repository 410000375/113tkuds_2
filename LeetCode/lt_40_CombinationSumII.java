import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 排序方便剪枝與去重
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 剪枝：target 減去當前數字小於0，後面更大就不用看
            if (candidates[i] > target) break;

            // 跳過重複元素，保證同一層不選同樣的數字
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, path, res); // i+1 因為每個元素只能用一次
            path.remove(path.size() - 1); // 回溯
        }
    }
}
