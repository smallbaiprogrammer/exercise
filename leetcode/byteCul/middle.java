package com.company.leetcode.byteCul;

public class middle {
    public int sumNums(int n) {
        boolean x = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;
    }
    public int reverseBits(int n) {
        int res=0;
        int temp=32;
        while(temp>0){
           res=res<<1|n&1;
           n=n>>>1;
           temp--;
        }
        return res;
    }
}
