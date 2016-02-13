package com.johnkuper.mentoring.factory;

/**
 * Created by Dmitriy_Korobeinikov on 4/28/2015.
 */
public enum DuckTypes {
    REAL_DUCK("real"), TOY_DUCK("toy");

    private String mName;

    DuckTypes(String name) {
        mName = name;
    }

    public static DuckTypes findByName(String name) {
        for (DuckTypes type : values()) {
            if (type.mName.equals(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("There is no duck type with name: " + name);
    }
}
