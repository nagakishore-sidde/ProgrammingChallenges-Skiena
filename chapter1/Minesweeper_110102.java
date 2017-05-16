/************* Problem Statement *************

 Have you ever played Minesweeper? This cute little game comes with a certain operating system whose name we can't remember.
 The goal of the game is to find where all the mines are located within a M x N field.

 The game shows a number in a square which tells you how many mines there are adjacent to that square.
 Each square has at most eight adjacent squares. The 4 x 4 field on the left contains two mines,
 each represented by a ``*'' character. If we represent the same field by the hint numbers described above,
 we end up with the field on the right:

 *...
 ....
 .*..
 ....

 *100
 2210
 1*10
 1110

 Input

 The input will consist of an arbitrary number of fields. The first line of each field contains two integers
 n and m ( 0 < n, m$ \le$100) which stand for the number of lines and columns of the field, respectively.
 Each of the next n lines contains exactly m characters, representing the field.

 Safe squares are denoted by ``.'' and mine squares by ``*,'' both without the quotes.
 The first field line where n = m = 0 represents the end of input and should not be processed.

 Output

 For each field, print the message Field #x: on a line alone, where x stands for the number of the field starting from 1.
 The next n lines should contain the field with the ``.'' characters replaced by the number of mines adjacent to that
 square. There must be an empty line between field outputs.

 Sample Input

 4 4
 *...
 ....
 .*..
 ....
 3 5
 **...
 .....
 .*...
 0 0

 Sample Output

 Field #1:
 *100
 2210
 1*10
 1110

 Field #2:
 **100
 33200
 1*100

 ******** End of Problem Statement *********/

package chapter1;

import java.util.Scanner;

public class Minesweeper_110102 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean doneProcessing = false;

        int filedCount = 1;
        while (!doneProcessing) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            if (m == 0 && n == 0) {
                doneProcessing = true;
                continue;
            }

            char[][] inputArr = new char[m][n];
            for (int i=0; i<m; i++) {
                String nextLine = sc.next();
                for (int j=0; j<nextLine.length(); j++) {
                    inputArr[i][j] = nextLine.charAt(j);
                }
            }

            if (filedCount > 1)
                System.out.println();

            int[][] result = getResult(inputArr, m, n);
            System.out.println("Field #" + filedCount++ + ":");
            for (int r=0; r<result.length; r++) {
                for (int c=0; c<result[0].length; c++) {
                    if (result[r][c] == -1)
                        System.out.print("*");
                    else
                        System.out.print(result[r][c]);
                }
                System.out.println();
            }
        }


    }

    public static int[][] getResult(char[][] input, int rowLen, int colLen) {
        int[][] res = new int[rowLen][colLen];

        for (int i=0; i<rowLen; i++) {
            for (int j=0; j<colLen; j++) {
                if (input[i][j] == '*') {
                    res[i][j] = -1;
                } else {
                    res[i][j] = getCount(input, i, j, rowLen, colLen);
                }
            }
        }

        return res;
    }

    public static int getCount(char[][] input, int i, int j, int rowLen, int colLen) {
        int count = 0;
        if (i > 0 && j > 0 && input[i-1][j-1] == '*') count++;
        if (i>0 && input[i-1][j] == '*') count++;
        if (i>0 && j+1 < colLen && input[i-1][j+1] == '*') count++;
        if (j > 0 && input[i][j-1] == '*') count++;
        if (j+1 < colLen && input[i][j+1] == '*') count++;
        if (i+1 < rowLen && j > 0 && input[i+1][j-1] == '*') count++;
        if (i+1 < rowLen && input[i+1][j] == '*') count++;
        if (i+1 < rowLen && j+1 < colLen && input[i+1][j+1] == '*') count++;

        return count;
    }
}