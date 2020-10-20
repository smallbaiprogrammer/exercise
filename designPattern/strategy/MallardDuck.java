package com.company.designPattern.strategy;

public class MallardDuck extends Duck {
    public MallardDuck(){
        flyBehavitor = new FlyWithWings();
        quackBehavitor = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}
