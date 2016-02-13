package com.johnkuper.mentoring.controller;

import com.johnkuper.mentoring.map.MapChangeListener;
import com.johnkuper.mentoring.map.MazeGenerator;
import com.johnkuper.mentoring.model.Duck;
import com.johnkuper.mentoring.utils.StringUtils;

import java.awt.event.KeyEvent;

import static com.johnkuper.mentoring.map.MazeGenerator.DUCK_CHAR;
import static com.johnkuper.mentoring.map.MazeGenerator.WALL_CHAR;

/**
 * Created by Dmitriy_Korobeinikov on 4/28/2015.
 */
public class BaseDuckController implements DuckController {
    protected String mMap;
    protected int mColumnCount;
    protected char mPreviousChar = MazeGenerator.PASSAGE_CHAR;
    protected boolean mIsMapChange;

    protected MapChangeListener mMapChangeListener;
    protected Duck mDuck;

    public BaseDuckController(Duck duck) {
        mDuck = duck;
    }

    protected String move(int shift, String direction) {
        if (mMap == null) {
            throw new IllegalStateException("You should set maze map before using duck controller");
        }
        int duckIndex = mMap.indexOf(DUCK_CHAR);
        char nextDuckChar = mMap.charAt(duckIndex + shift);
        if (nextDuckChar == WALL_CHAR) {
            return mMap;
        }
        System.out.println(direction);
        mMap = StringUtils.swapDuckChar(mMap, duckIndex, duckIndex + shift, mPreviousChar);
        mIsMapChange = true;
        mPreviousChar = nextDuckChar;
        mDuck.makeMove(nextDuckChar);
        return mMap;
    }

    protected void parseMove(int movementCode) {
        switch (movementCode) {
            case KeyEvent.VK_RIGHT:
                mMap = move(1, "RIGHT");
                notifyView();
                break;
            case KeyEvent.VK_LEFT:
                mMap = move(-1, "LEFT");
                notifyView();
                break;
            case KeyEvent.VK_UP:
                mMap = move(-mColumnCount, "UP");
                notifyView();
                break;
            case KeyEvent.VK_DOWN:
                mMap = move(mColumnCount, "DOWN");
                notifyView();
                break;
        }
    }

    protected void notifyView() {
        if (mIsMapChange) {
            mMapChangeListener.onMapChange(mMap);
            mIsMapChange = false;
        }
    }

    @Override
    public void setMapChangeListener(MapChangeListener mapChangeListener) {
        mMapChangeListener = mapChangeListener;
    }

    @Override
    public void setMazeMap(String mazeMap) {
        mMap = mazeMap;
        mColumnCount = mazeMap.indexOf(MazeGenerator.CORNER_CHAR) + 1;
    }
}
