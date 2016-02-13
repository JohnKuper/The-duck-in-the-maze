package com.johnkuper.mentoring.observer;

import com.johnkuper.mentoring.model.DuckState;

/**
 * Created by Dmitriy_Korobeinikov on 4/22/2015.
 */
public class HealthPointObserver implements IObserver {

    private DuckSubject mDuckSubject;

    @Override
    public void update(DuckState protocol) {
        if (protocol.healthPoint == 0) {
            mDuckSubject.satisfy();
        }
    }

    @Override
    public void setSubject(DuckSubject subject) {
        mDuckSubject = subject;
    }
}
