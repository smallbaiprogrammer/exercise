package com.company.designPattern.strategy;

public class ModelDuck extends Duck{
    public ModelDuck(){
        flyBehavitor = new FlyWithWings();
        quackBehavitor = new MuteQuack();
    }
    @Override
    public void display() {
        System.out.println("I'm a model duck");
    }
}
