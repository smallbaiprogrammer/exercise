package com.company.designPattern.strategy;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        duck.display();
        duck.performfly();
        duck.performSwim();
    }
}
