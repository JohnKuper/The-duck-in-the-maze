package com.johnkuper.mentoring.model;

import com.johnkuper.mentoring.controller.resolver.ToyDuckMoveResolver;

/**
 * Created by Dmitriy_Korobeinikov on 4/21/2015.
 */
public class ToyDuck extends Duck {

    public ToyDuck() {
        super();
        mMoveResolver = new ToyDuckMoveResolver(this);
    }

    public void waveWings() {
        System.out.println("I'm waving my wings");
    }

    public void changeBattery() {
        System.out.println("Battery was changed");
        mHealthPoint = 10;
    }

    @Override
    protected void reduceHealthPoint() {
        mHealthPoint -= 0.5;
    }

    @Override
    public void satisfy() {
        changeBattery();
    }
}
