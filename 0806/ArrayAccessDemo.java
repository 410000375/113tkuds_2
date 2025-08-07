import java.util.Arrays;

public class ArrayAccessDemo {
    public static void main(String[] args) {
        int[] scores = {85, 92, 77, 60, 88};
        
        
        int firstScore = scores[0];     
        int lastScore = scores[4];      
        int middleScore = scores[2];    
        
        System.out.println("第一個成績：" + firstScore);
        System.out.println("最後一個成績：" + lastScore);
        System.out.println("中間成績：" + middleScore);
        
        
        scores[0] = 90;                 
        scores[scores.length - 1] = 95; 
        
        
        int arrayLength = scores.length;
        
        System.out.println("\n修改後的陣列：");
        for (int i = 0; i < arrayLength; i++) {
            System.out.printf("索引 %d：%d\n", i, scores[i]);
        }
        
        System.out.println("陣列內容：" + Arrays.toString(scores));
    }
}
