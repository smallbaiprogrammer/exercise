package com.company.designPattern.strategy;

public class FlyNoWay implements FlyBehavitor{
    @Override
    public void fly() {
        System.out.println("I can't fly!");
    }
}
