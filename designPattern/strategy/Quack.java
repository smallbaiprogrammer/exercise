package com.company.designPattern.strategy;

public class Quack implements QuackBehavitor{
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
