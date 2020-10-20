package com.company.designPattern.strategy;

public class MuteQuack implements QuackBehavitor {
    @Override
    public void quack() {
        System.out.println("I can't quack!");
    }
}
