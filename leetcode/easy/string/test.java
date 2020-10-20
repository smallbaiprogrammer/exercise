package com.company.leetcode.easy.string;

import java.util.*;

public class test {
    public int dayOfYear(String date) {
        String[] dates = new String[3];
        int[] days={31,28,31,30,31,30,31,31,30,31,30,31};
        int day=0;
        dates = date.split("-");
        if ((Integer.parseInt(dates[0])%4==0&&Integer.parseInt(dates[0])%100!=0)||Integer.parseInt(dates[0])%400==0) {
            days[1]+=1;
        }
        for (int i=0;i<Integer.parseInt(dates[1])-1;i++){
            day+=days[i];
        }
        return day+Integer.parseInt(dates[2]);
    }
    public static void reverseString(char[] s) {
        Stack<Character> stack = new Stack<>();
        for (char c:s){
            stack.push(c);
        }
        int i=0;
        while (!stack.isEmpty()){
            s[i]=stack.pop();
            i++;
        }
    }

    /**
     * 字符串相加
     */
    public static String addStrings(String num1, String num2) {
        int carry = 0 ;
        String ans ="";
        int pos1 = num1.length()-1;
        int pos2 = num2.length()-1;
        while(pos1>=0||pos2>=0||carry!=0){
            int x = pos1>=0?num1.charAt(pos1)-'0':0;
            int y = pos2>=0?num2.charAt(pos2)-'0':0;
            int sum= x+y+carry;
            ans = sum%10+ans;
            carry = sum /10;
            pos1--;
            pos2--;
        }
        return ans;
    }
    // 一位数乘以一位数
    public static String onemultiply(char num1,char num2){
        return String.valueOf((num1-'0')*(num2-'0'));
    }
    // 字符串相乘 多位数乘以多位数
    public static String multiply(String num1, String num2) {
        if (num1.equals("0")||num2.equals("0")){
            return "0";
        }
        String ans="";
        int pos2=num2.length()-1;
        while (pos2>=0){
            String tem ="";
            int pos1=num1.length()-1;
            while (pos1>=0){
                String temp = onemultiply(num1.charAt(pos1),num2.charAt(pos2));
                for (int i=0;i<num1.length()-pos1-1;i++){
                    temp = temp+"0";
                }
                tem = addStrings(tem,temp);
                pos1--;
            }
            for (int i=0;i<num2.length()-pos2-1;i++){
                tem = tem+"0";
            }
            ans=addStrings(ans,tem);
            pos2--;
        }
        return ans;
    }
    public static char firstUniqChar(String s) {
        if (s.equals("")){
            return ' ';
        }
        for (char i:s.toCharArray()){
            if (s.indexOf(i)==s.lastIndexOf(i)){
                return i;
            }
        }
        return ' ';
    }
    //验证回文串
    public static boolean isPalindrome(String s) {
        s=s.trim();
        int i=0;
        int j=s.length()-1;
        while (i<j){
            while (i<j&&!((s.charAt(i)>='a'&&s.charAt(i)<='z')||(s.charAt(i)>='A'&&s.charAt(i)<='Z')||(s.charAt(i)>='0'&&s.charAt(i)<='9'))){
                i++;
            }
            while (i<j&&!((s.charAt(j)>='a'&&s.charAt(j)<='z')||(s.charAt(j)>='A'&&s.charAt(j)<='Z')||(s.charAt(j)>='0'&&s.charAt(j)<='9'))){
                j--;
            }
            if (String.valueOf(s.charAt(i)).equalsIgnoreCase(String.valueOf(s.charAt(j)))){
                i++;
                j--;
            }else {
                return false;
            }
        }
        return true;
    }
    public static String convertToTitle(int n) {
        StringBuilder ans = new StringBuilder();
        while (n>0){
            n--;
            ans.append((char)('A'+(n%26)));
            n/=26;
        }
        return ans.reverse().toString();
    }
    public static String convertToTitle1(int n) {
        String[] strs={"A","B","C","D","E","F","G","H","I","J","K","L","" +
                "M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String str = Integer.toString(n,26);
        StringBuilder ans = new StringBuilder();
        for (int i=0;i<str.length();i++){
            ans.append(strs[Integer.parseInt(String.valueOf(str.charAt(i)),26)]);
        }
        return ans.reverse().toString();
    }

    public static List<String> commonChars(String[] A) {
        List<String> ans = new LinkedList<>();
        Map<Character,Integer> map = new HashMap<>();
        char[] cs = A[0].toCharArray();
        for (int i=0;i<cs.length;i++){
            map.put(cs[i],map.getOrDefault(cs[i],0)+1);
        }
        for (int i=1;i<A.length;i++){
            Map<Character,Integer> temp = new HashMap<>();
            char[] chars = A[i].toCharArray();
            for (int j=0;j<chars.length;j++){
                temp.put(chars[j],temp.getOrDefault(chars[j],0)+1);
            }
            System.out.println("sssssss");
            for (Object obj:temp.entrySet()){
            System.out.println(obj);
            }
            for (Character c:map.keySet()){
                map.put(c,Math.min(map.get(c),temp.getOrDefault(c,0)));
            }
        }
//        System.out.println("assssss");
//        for (Object obj:map.entrySet()){
//            System.out.println(obj);
//        }

        for (char c:map.keySet()){
            int i=map.get(c);
            while (i>0){
                ans.add(c+"");
                i--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] s = new String[]{"bella", "label", "roller"};
        List<String> ans = commonChars(s);
        for (String str:ans){
            System.out.println(str);
        }
    }
    }
