package com.johnkuper.mentoring.factory;

import com.johnkuper.mentoring.model.Duck;
import com.johnkuper.mentoring.model.RealDuck;
import com.johnkuper.mentoring.model.ToyDuck;
import com.johnkuper.mentoring.observer.ExitObserver;
import com.johnkuper.mentoring.observer.HealthPointObserver;

/**
 * Created by Dmitriy_Korobeinikov on 4/28/2015.
 */
public class DuckFactory {

    public static Duck getDuck(DuckTypes type) {
        Duck duck = null;
        switch (type) {
            case REAL_DUCK:
                duck = new RealDuck();
                break;
            case TOY_DUCK:
                duck = new ToyDuck();
                break;
        }
        duck.register(new HealthPointObserver());
        duck.register(new ExitObserver());
        return duck;
    }
}
