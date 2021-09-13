


/*
Tianlin Yang, 11/13/2021
 */
/*
Question A

Your goal for this question is to write a program that accepts two lines (x1,x2) and (x3,x4) on the x-axis and returns
whether they overlap.
As an example, (1,5) and (2,6) overlaps but not (1,5) and (6,8).
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class questionA {
    public static void main(String[] args) {

        int[] line1 = new int[]{1,2};
        int[] line2 = new int[]{2,3};
        questionA qA = new questionA();
        System.out.println(qA.isOverlap(line1,line2));


        //Test case 1: boundary condition, no length, should be false, no valid line here
        line1 = line2 = new int[]{0,0};
        System.out.println("Test1 line1(0,0) line2(0,0): "+qA.isOverlap(line1,line2));
        line1 = line2 = new int[]{1,1};
        System.out.println("      line1(1,1) line2(1,1): "+qA.isOverlap(line1,line2));
        line1 = line2 = new int[]{-1,-1};
        System.out.println("      line1(-1,-1) line2(-1,-1): "+qA.isOverlap(line1,line2));
        //Test case 2: one line included in other line
        line1 = new int[]{1,2};
        line2 = new int[]{0,3};
        System.out.println("Test2 line1(1,2) line2(0,3): "+qA.isOverlap(line1,line2));
        //Test case 3: same line
        line1 = new int[]{-5,5};
        line2 = new int[]{5,-5};
        System.out.println("Test3 line1(-5,5) line2(5,-5): "+qA.isOverlap(line1,line2));
        //Test case 4: no overlap
        line1 = new int[]{-5,-4};
        line2 = new int[]{-3,-2};
        System.out.println("Test4 line1(-5,-4) line2(-3,-2): "+qA.isOverlap(line1,line2));
        line1 = new int[]{5,4};
        line2 = new int[]{3,2};
        System.out.println("      line1(5,4) line2(3,2): "+qA.isOverlap(line1,line2));
        line1 = new int[]{-5,-4};
        line2 = new int[]{3,2};
        System.out.println("      line1(-5,-4) line2(3,2): "+qA.isOverlap(line1,line2));
        line1 = new int[]{-5,-4};
        line2 = new int[]{-4,4};
        System.out.println("      line1(-5,-4) line2(3,2): "+qA.isOverlap(line1,line2));
        //Test case 5: overlap
        line1 = new int[]{-5,5};
        line2 = new int[]{-10,-4};
        System.out.println("Test4 line1(-5,5) line2(-10,-4): "+qA.isOverlap(line1,line2));
    }

    boolean isOverlap(int[] l1, int[] l2) {
        //step1: sort the inputs coordinates as asc order
        Arrays.sort(l1);
        Arrays.sort(l2);
        //step2: find any overlap by largest x-axis position - smallest x-axis position
        //       case1 - overlap exist, line1.length + line2.length > largest position - smallest position
        //       case2 - no overlap, line1.length + line2.length <= largest position - smallest position
        int largestPos = Math.max(l1[1],l2[1]);
        int smallestPos = Math.min(l1[0],l2[0]);
        if( l1[1] - l1[0] + l2[1] - l2[0] > largestPos - smallestPos){
            return true;
        }else{
            return false;
        }
    }

}
