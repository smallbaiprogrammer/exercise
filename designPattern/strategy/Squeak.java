package com.company.designPattern.strategy;

public class Squeak implements QuackBehavitor{
    @Override
    public void quack() {
        System.out.println("squeak");
    }
}
