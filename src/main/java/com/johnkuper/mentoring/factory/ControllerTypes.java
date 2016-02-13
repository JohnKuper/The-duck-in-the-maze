package com.johnkuper.mentoring.factory;

/**
 * Created by Dmitriy_Korobeinikov on 4/28/2015.
 */
public enum ControllerTypes {
    MANUAL_CONTROLLER("manual"), AI_CONTROLLER("ai");

    private String mName;

    ControllerTypes(String name) {
        mName = name;
    }

    public static ControllerTypes findByName(String name) {
        for (ControllerTypes type : values()) {
            if (type.mName.equals(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("There is no controller type: " + name);
    }
}
