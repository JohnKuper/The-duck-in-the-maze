package com.johnkuper.mentoring.factory;

import com.johnkuper.mentoring.controller.AIDuckController;
import com.johnkuper.mentoring.controller.DuckController;
import com.johnkuper.mentoring.controller.ManualDuckController;
import com.johnkuper.mentoring.model.Duck;

/**
 * Created by Dmitriy_Korobeinikov on 4/28/2015.
 */
public class ControllerFactory {
    public static DuckController getController(ControllerTypes type, Duck duck) {
        switch (type) {
            case MANUAL_CONTROLLER:
                return new ManualDuckController(duck);
            case AI_CONTROLLER:
                return new AIDuckController(duck);
            default:
                throw new IllegalArgumentException("There is no controller type: " + type);
        }
    }
}
