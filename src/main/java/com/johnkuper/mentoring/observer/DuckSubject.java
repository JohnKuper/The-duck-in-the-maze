package com.johnkuper.mentoring.observer;

/**
 * Created by Dmitriy_Korobeinikov on 4/22/2015.
 */
public interface DuckSubject<T> {
    void register(IObserver observer);

    void unregister(IObserver object);

    void notifyObservers();

    void satisfy();
}
