package com.johnkuper.mentoring.model;

import com.johnkuper.mentoring.controller.resolver.DuckMoveResolver;
import com.johnkuper.mentoring.observer.IObserver;
import com.johnkuper.mentoring.observer.DuckSubject;

import java.util.ArrayList;

/**
 * Created by Dmitriy_Korobeinikov on 4/21/2015.
 */
public abstract class Duck implements DuckSubject {
    protected ArrayList<IObserver> mObservers;
    protected boolean mIsChange;
    protected double mHealthPoint;
    protected char mStandOn;
    protected DuckMoveResolver mMoveResolver;

    public Duck() {
        mObservers = new ArrayList<>();
        mHealthPoint = 10;
    }

    public void quack() {
        System.out.println("quack quack...");
    }

    public void walk(char toChar) {
        System.out.println("I'm walking");
        reduceHealthPoint();
        mStandOn = toChar;
        mIsChange = true;
        notifyObservers();
    }

    public void makeMove(char toChar) {
        mMoveResolver.resolveMove(toChar);
    }

    @Override
    public void register(IObserver observer) {
        if (observer == null) {
            throw new NullPointerException("Observer can't be null");
        }
        if (!mObservers.contains(observer)) {
            mObservers.add(observer);
            observer.setSubject(this);
        }
    }

    @Override
    public void unregister(IObserver observer) {
        mObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        if (!mIsChange) {
            return;
        }
        DuckState protocol = new DuckState(mHealthPoint, mStandOn);
        for (IObserver observer : mObservers) {
            observer.update(protocol);
        }
        mIsChange = false;
    }

    @Override
    public void satisfy() {
    }

    protected abstract void reduceHealthPoint();
}
