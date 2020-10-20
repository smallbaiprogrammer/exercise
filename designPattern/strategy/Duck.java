package com.company.designPattern.strategy;

public abstract class Duck {
    FlyBehavitor flyBehavitor;
    QuackBehavitor quackBehavitor;
    public Duck(){

    }
    public abstract void display();
    public void performfly(){
        flyBehavitor.fly();
    }
    public void performQuack(){
        quackBehavitor.quack();
    }public void performSwim(){
        System.out.println("all ducks float，even decoys！");
    }

    public void setFlyBehavitor(FlyBehavitor flyBehavitor) {
        this.flyBehavitor = flyBehavitor;
    }

    public void setQuackBehavitor(QuackBehavitor quackBehavitor) {
        this.quackBehavitor = quackBehavitor;
    }
}
