package hashset.verifysudokuboard;

import java.util.ArrayList;
import java.util.HashSet;

// Link: https://leetcode.com/problems/valid-sudoku/
public class SudokuBoard {

    public boolean isValidSudoku(char[][] board) {

        // Each row must contain the digits 1-9 without repetition.
        for(int i = 0; i < board.length; i++){

            boolean[] rowLookUp = new boolean[10]; //Set<Character> rowLookUp = new HashSet<>();

            for(int j = 0; j < board[i].length; j++){

                if(board[i][j] != '.'){

                    if(rowLookUp[board[i][j] - '0'] // (rowLookUp.contains(board[i][j])
                        // || board[i][j] - '0' < 1 || board[i][j] - '0' > 9
                    ){
                        return false;
                    }
                    rowLookUp[board[i][j] - '0'] = true; //rowLookUp.add(board[i][j]);
                }
            }
        }

        // Each column must contain the digits 1-9 without repetition.
        for(int j = 0; j < board[0].length; j++){
            boolean[] colLookUp = new boolean[10]; //Set<Character> colLookUp = new HashSet<>();
            for(int i = 0; i < board[j].length; i++){

                if(board[i][j] != '.'){

                    if(colLookUp[board[i][j] - '0'] // (colLookUp.contains(board[i][j])
                        // || board[i][j] - '0' < 1 || board[i][j] - '0' > 9
                    ){
                        return false;
                    }
                    colLookUp[board[i][j] - '0'] = true; //colLookUp.add(board[i][j]);
                }
            }
        }

        // Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
        for(int i = 0; i < board.length; i+=3){
            for(int j = 0; j < board[i].length; j+=3){

                boolean[] boxLookUp = new boolean[10]; //Set<Character> boxLookUp = new HashSet<>();

                for(int k = i; k <= i + 2; k++){
                    for(int l = j; l <= j + 2; l++){

                        if(board[k][l] != '.'){

                            if(boxLookUp[board[k][l] - '0'] //(boxLookUp.contains(board[k][l])
                                // || board[k][l] - '0' < 1 || board[k][l] - '0' > 9
                            ){
                                return false;
                            }
                            boxLookUp[board[k][l] - '0'] = true; //boxLookUp.add(board[k][l]);
                        }
                    }
                }
            }
        }

        return true;
    }

    public boolean verify_sudoku_board(ArrayList<ArrayList<Integer>> board) {
        // Create hash sets for each row, column, and subgrid to keep track of numbers
        // previously seen on any given row, column, or subgrid.
        ArrayList<HashSet<Integer>> row_sets = new ArrayList<>();
        ArrayList<HashSet<Integer>> column_sets = new ArrayList<>();
        HashSet<Integer>[][] subgrid_sets = new HashSet[3][3];
        for (int i = 0; i < 9; i++) {
            row_sets.add(new HashSet<>());
            column_sets.add(new HashSet<>());
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                subgrid_sets[i][j] = new HashSet<>();
            }
        }
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int num = board.get(r).get(c);
                if (num == 0) {
                    continue;
                }
                // Check if 'num' has been seen in the current row, column, or subgrid.
                if (row_sets.get(r).contains(num)) {
                    return false;
                }
                if (column_sets.get(c).contains(num)) {
                    return false;
                }
                if (subgrid_sets[r / 3][c / 3].contains(num)) {
                    return false;
                }
                // If we passed the above checks, mark this value as seen by adding it to
                // its corresponding hash sets.
                row_sets.get(r).add(num);
                column_sets.get(c).add(num);
                subgrid_sets[r / 3][c / 3].add(num);
            }
        }
        return true;
    }
}
