package com.johnkuper.mentoring.observer;

import com.johnkuper.mentoring.model.DuckState;

/**
 * Created by Dmitriy_Korobeinikov on 4/22/2015.
 */
public interface IObserver {

    void update(DuckState protocol);

    void setSubject(DuckSubject subject);
}
