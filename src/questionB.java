/*
Tianlin Yang, 11/13/2021
 */
/*
Question B

The goal of this question is to write a software library that accepts 2 version string as input and returns whether one
is greater than, equal, or less than the other. As an example: “1.2” is greater than “1.1”.
Please provide all test cases you could think of.
 */

import java.util.Arrays;

public class questionB {
    public static void main(String[] args) {
        //Assume the version string follow standard format such as:
        // %int . %int    int in [1,+]
        // for example 99999999.9999999 and 1.1 and 1.1.2.15
        String str1 = "1.1";
        String str2 = "1.2";
        questionB qB = new questionB();
        System.out.println(qB.str1Bigger(str1,str2));


        //Test case 1: String1 lower
        str1 = "1.1.2";
        str2 = "2.1.2";
        System.out.println("test1: "+qB.str1Bigger(str1,str2));
        str1 = "1.1.2";
        str2 = "1.2.2";
        System.out.println("       "+qB.str1Bigger(str1,str2));
        str1 = "1.1.2";
        str2 = "1.1.3";
        System.out.println("       "+qB.str1Bigger(str1,str2));
        str1 = "1.1";
        str2 = "1.1.1";
        System.out.println("       "+qB.str1Bigger(str1,str2));
        //Test case 2: String1 greater
        str1 = "2.1.2";
        str2 = "1.1.2";
        System.out.println("test2: "+qB.str1Bigger(str1,str2));
        str1 = "1.2.2";
        str2 = "1.1.2";
        System.out.println("       "+qB.str1Bigger(str1,str2));
        str1 = "1.1.3";
        str2 = "1.1.2";
        System.out.println("       "+qB.str1Bigger(str1,str2));
        str1 = "1.1.1.1";
        str2 = "1.1.1";
        System.out.println("       "+qB.str1Bigger(str1,str2));
        //Test case 3: same input
        str1 = "1.1.2";
        str2 = "1.1.2";
        System.out.println("test3: "+qB.str1Bigger(str1,str2));
        str1 = "1";
        str2 = "1";
        System.out.println("       "+qB.str1Bigger(str1,str2));
        //Test case 4: empty input
        str1 = "";
        str2 = "1.1.2";
        System.out.println("test4: "+qB.str1Bigger(str1,str2));
        str1 = "";
        str2 = "";
        System.out.println("       "+qB.str1Bigger(str1,str2));
    }

    String str1Bigger(String s1, String s2) {
        if(s1.length()==0||s2.length()==0){
            return "Empty inputs: "+s1+","+s2;
        }
        //step1: split the version code with n parts, before '.' and after it.
        String[] str1 = s1.split("\\.");
        String[] str2 = s2.split("\\.");
        //Step2: find smaller string
        int min = Math.min(str1.length,str2.length);
        //System.out.println("min: "+min+" str1: "+str1[0]);
        //Step3: traversal the digits from left to right based on smaller string.
        for (int i = 0; i < min; i++) {

            int a = Integer.parseInt(str1[i]);
            int b = Integer.parseInt(str2[i]);

            if(a>b){
                return s1 + " greater than " + s2;
            }
            if(a<b){
                return s1 + " less than " + s2;
            }
        }
        //Step4: consider all previous comparisons are same
        if(str1.length==str2.length){
            return s1+ " equal to " + s2;
        }
        if(str1.length>str2.length){
            return s1 + " greater than " + s2;
        }else{
            return s1 + " less than " + s2;
        }

    }

}
