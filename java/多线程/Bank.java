package com.company.java.多线程;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    private Lock banklock;
    private Condition sufficientFunds;
    public Bank(int n,double initialBalance){
        accounts = new double[n];
        Arrays.fill(accounts,initialBalance);
        banklock = new ReentrantLock();
        sufficientFunds = banklock.newCondition();
    }
    public void transfer(int from,int to,double amount) throws InterruptedException {
        banklock.lock();
        try{
        while (accounts[from]<amount){
            sufficientFunds.await();
        }
        System.out.println(Thread.currentThread());
        System.out.printf(" %10.2f from %d to %d",amount,from,to);
        accounts[from]-=amount;
        System.out.printf(" Total Balance: %10.2f%n",getTotalBalance());
        accounts[to]+=amount;
        sufficientFunds.signalAll();
        }
        finally {
            banklock.unlock();
        }
    }
    public double getTotalBalance(){
        banklock.lock();
        try{
            double sum=0;
            for (double a:accounts){
                sum+=a;
            }
            return sum;
        }finally {
            banklock.unlock();
        }
    }
    public synchronized int size(){
        return accounts.length;

    }
}
