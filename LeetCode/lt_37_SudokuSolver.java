class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }
    
    private boolean backtrack(char[][] board, int row, int col) {
        if (row == 9) return true; // 完成填充
        
        if (col == 9) return backtrack(board, row + 1, 0); // 換行
        
        if (board[row][col] != '.') 
            return backtrack(board, row, col + 1); // 已有數字，跳過
        
        for (char c = '1'; c <= '9'; c++) {
            if (isValid(board, row, col, c)) {
                board[row][col] = c;
                if (backtrack(board, row, col + 1)) return true;
                board[row][col] = '.'; // 回溯
            }
        }
        return false; // 無法填入
    }
    
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // 檢查行
            if (board[row][i] == c) return false;
            // 檢查列
            if (board[i][col] == c) return false;
            // 檢查 3x3 宮
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == c) return false;
        }
        return true;
    }
}
