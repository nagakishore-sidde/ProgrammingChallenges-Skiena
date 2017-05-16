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

public class Minesweeper_110102_Optimized {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int fieldCount = 1;

        while (true) {
            int m = sc.nextInt(), n = sc.nextInt();
            if (m == 0 && n == 0) return;
            sc.nextLine(); // Make sure to move to next line.

            if (fieldCount > 1) System.out.println(); // Print new line only for second field onwards.
            System.out.println("Field #" + fieldCount + ":");

            // Use just previous, current and next lines to compute the answer. No need of two dimensional array.
            String curLine = sc.nextLine();
            String prevLine = null;
            for (int row = 0; row < m; row++) {
                // Make sure to reset the nextLine to null when the last row of any field is reached.
                String nextLine = (row < m - 1) ? sc.nextLine() : null;

                for (int col = 0; col < curLine.length(); col++) {
                    if (curLine.charAt(col) == '*') {
                        System.out.print("*");
                    } else {
                        int count = 0;
                        if (prevLine != null) {
                            if (col > 0 && prevLine.charAt(col - 1) == '*') count++;
                            if (col < prevLine.length() && prevLine.charAt(col) == '*') count++;
                            if (col + 1 < prevLine.length() && prevLine.charAt(col + 1) == '*') count++;
                        }

                        if (nextLine != null) {
                            if (col > 0 && nextLine.charAt(col - 1) == '*') count++;
                            if (nextLine.charAt(col) == '*') count++;
                            if (col + 1 < nextLine.length() && nextLine.charAt(col + 1) == '*') count++;
                        }

                        if (col > 0 && curLine.charAt(col - 1) == '*') count++;
                        if (col + 1 < curLine.length() && curLine.charAt(col + 1) == '*') count++;

                        System.out.print(count);
                    }
                }
                System.out.println();
                // Make sure to reset the prevLine and curLine appropriately.
                prevLine = curLine;
                curLine = nextLine;
            }

            fieldCount++;
        }
    }
}