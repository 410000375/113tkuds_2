import java.util.*;

public class LC39_CombinationSum_PPE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] candidates = new int[n];
        for(int i = 0; i < n; i++) candidates[i] = sc.nextInt();
        sc.close();
        Arrays.sort(candidates); // 方便組內升序

        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);

        for(List<Integer> comb : res){
            for(int i=0;i<comb.size();i++){
                if(i>0) System.out.print(" ");
                System.out.print(comb.get(i));
            }
            System.out.println();
        }
    }

    static void backtrack(int[] nums, int target, int start, List<Integer> comb, List<List<Integer>> res){
        if(target == 0){
            res.add(new ArrayList<>(comb));
            return;
        }
        for(int i = start; i < nums.length; i++){
            if(nums[i] > target) break;
            comb.add(nums[i]);
            backtrack(nums, target - nums[i], i, comb, res); // 可重複選
            comb.remove(comb.size() - 1);
        }
    }
}
