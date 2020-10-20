package com.company.leetcode.middle.string;

import java.util.HashSet;
import java.util.Set;

public class test {
    public String longestPalindrome(String s) {
        String res ="";
        if (s==null||s==""){
            return res;
        }
        int i=0;
        int j=s.length()-1;
        while(i<j){
            if (s.charAt(i)==s.charAt(j)){
                if (judge(s.substring(i,j))){
                    return s.substring(i,j);
                }
            }else{

            }
        }
        return  res;
    }

    public static int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for(String str:emails){
            int location = str.indexOf("@");
            String a = str.substring(0,location);
            String b = str.substring(location+1);
            a= a.replace(".","");
            if (a.indexOf("+")!=-1){
                a=a.substring(0,a.indexOf("+"));
            }

            a=a+"@"+b;
            System.out.println(a);
            set.add(a);
        }
        return set.size();
    }
    public static String shortestPalindrome(String s) {
        int i;
        for (i=s.length();i>=0;i--){
            String temp = s.substring(0,i);
            if (judge(temp)){
                break;
            }
        }
        String temp = s.substring(i);
        for (int j=0;j<temp.length();j++){
            s=temp.charAt(j)+s;
        }
        return s;
    }
    public static boolean judge(String str){
        for (int i=0;i<str.length()/2;i++){
            if (str.charAt(i)!=str.charAt(str.length()-1-i)){
                return false;
            }
        }
        return true;
    }
    // 数字转为罗马数组字符串，范围在0-3999，编码思维
    public String intToRoman1(int num) {
        // 千位数组 0，1，2，3
        String[] thousands = {"", "M", "MM", "MMM"};
        // 百位数组 0，1，2，3，4，5，6，7，8，9
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        // 十位数组
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        // 个位数组
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        // 数码转换
        StringBuilder res =new StringBuilder();
        res.append(thousands[num / 1000]);
        res.append(hundreds[num % 1000 / 100]);
        res.append(tens[num % 100 / 10]);
        res.append(ones[num % 10]);
        return res.toString();
    }

    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    // 贪心算法 贪心算法的速度比较快
    // 这个思想容易一点
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        // Loop through each symbol, stopping if num becomes 0.
        for (int i = 0; i < values.length && num >= 0; i++) {
            // Repeat while the current symbol still fits into num.
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
    // 字符串转为数字，若移除返回最大数，
    public static int strToInt(String str) {
        str=str.trim();
        if(str.length()==0){
            return 0;
        }
        char[] strs = str.toCharArray();
        boolean f=true;
        int i=0;
        int sum=0;
        if (strs[i]=='-'){
            f=false;
            i++;
        }else if (strs[i]=='+'){
            f=true;
            i++;
        }
        for (;i<strs.length;i++){
            if(strs[i]>='0'&&strs[i]<='9') {
                if (sum>=Integer.MAX_VALUE/10){
                    if (f) {
                        if(sum==Integer.MAX_VALUE/10&&strs[i]-'0'<=7){
                        }else{
                            return Integer.MAX_VALUE;
                        }
                    }else{
                        if(sum==Integer.MAX_VALUE/10&&strs[i]-'0'<=8){

                        }else{
                            return Integer.MIN_VALUE;
                        }
                    }
                }
                sum=sum*10+strs[i]-'0';
            }else if(sum==0){
                return 0;
            }else{
                break;
            }
        }
        if (!f){
            return sum*-1;
        }
        return sum;
    }
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
       System.out.println(strToInt("2147483648"));
    }
}
