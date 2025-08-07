
public class ArrayDeclarationDemo {
    public static void main(String[] args) {
        
        int[] scores;           
        scores = new int[5];    
        
        
        int[] grades = new int[5];  
        
        
        int[] numbers = {85, 92, 77, 60, 88};  
        
        
        int[] values = new int[]{10, 20, 30, 40, 50};
        
        System.out.println("各陣列的長度：");
        System.out.println("scores: " + scores.length);
        System.out.println("grades: " + grades.length);
        System.out.println("numbers: " + numbers.length);
        System.out.println("values: " + values.length);
    }
}
