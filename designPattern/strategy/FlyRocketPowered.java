package com.company.designPattern.strategy;

public class FlyRocketPowered implements FlyBehavitor{
    @Override
    public void fly() {
        System.out.println("I can fly with rocket");
    }
}
