package com.johnkuper.mentoring.model;

import com.johnkuper.mentoring.controller.resolver.RealDuckMoveResolver;

/**
 * Created by Dmitriy_Korobeinikov on 4/21/2015.
 */
public class RealDuck extends Duck {

    public RealDuck() {
        super();
        mMoveResolver = new RealDuckMoveResolver(this);
    }

    public void fly(char toChar) {
        System.out.println("I'm flying");
        reduceHealthPoint();
        mStandOn = toChar;
        mIsChange = true;
        notifyObservers();
    }

    public void swim(char toChar) {
        System.out.println("I'm swimming");
        reduceHealthPoint();
        mStandOn = toChar;
        mIsChange = true;
        notifyObservers();
    }

    public void eat() {
        System.out.println("I'm eating");
        mHealthPoint = 10;
    }

    public void drink() {
        System.out.println("I'm drinking");
        mHealthPoint = 10;
    }

    @Override
    public void satisfy() {
        eat();
    }

    @Override
    protected void reduceHealthPoint() {
        mHealthPoint -= 1;
    }
}
