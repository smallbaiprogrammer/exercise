package com.company.designPattern.strategy;

public class FlyWithWings implements FlyBehavitor{
    @Override
    public void fly() {
        System.out.println("I can fly");
    }
}
