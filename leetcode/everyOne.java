package com.company.leetcode;

public class everyOne {
    // 垃圾题
    public static boolean backspaceCompare(String S, String T) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        char[] chars1 = S.toCharArray();
        char[] chars2 = T.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        for (int i=0;i<len1;i++){
            if (chars1[i]=='#'){
                if (s1.length()>0){
                    s1.deleteCharAt(s1.length()-1);
                }
            }else {
                s1.append(chars1[i]);
            }
        }

        for (int i=0;i<len2;i++){
            if (chars2[i]=='#'){
                if (s2.length()>0) {
                    s2.deleteCharAt(s2.length()-1);
                }
            }else {
                s2.append(chars2[i]);
            }
        }
        return s1.toString().equals(s2.toString());
    }

    public static void main(String[] args) {
        String s1 = "abc#d";
        String s2 = "ab #d";
        System.out.println(backspaceCompare(s1,s2));
    }
}
