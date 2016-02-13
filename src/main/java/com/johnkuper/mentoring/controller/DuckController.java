package com.johnkuper.mentoring.controller;

import com.johnkuper.mentoring.map.MapChangeListener;

/**
 * Created by Dmitriy_Korobeinikov on 4/22/2015.
 */
public interface DuckController {
    void setMapChangeListener(MapChangeListener mapChangeListener);

    void setMazeMap(String mazeMap);
}
