package com.company.java.多线程;

import jdk.nashorn.internal.ir.Block;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test {
    public static volatile int sum=0;
    private Lock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i=0;i<threads.length;i++){
            threads[i]= new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j=0;j<1000;j++){
                        change();
                    }
                }
            });
            threads[i].start();
        }
        // 这里面是重点
        // 因为这10个线程没有执行完
        for (Thread thread:threads){
           thread.join();
        }
        System.out.println(sum);
    }

    public static void change(){
            sum++;
    }

}
