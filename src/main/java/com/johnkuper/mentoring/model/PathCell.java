package com.johnkuper.mentoring.model;

/**
 * Created by Dmitriy_Korobeinikov on 4/28/2015.
 */
public class PathCell {
    private int g;
    private int h;
    private int f;
    private int mIndex;
    public PathCell parentPathCell;

    public PathCell() {
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
        setF();
    }

    public void setH(int h) {
        this.h = h;
        setF();
    }

    public int getF() {
        return f;
    }

    public void setF() {
        if (h + g > 0) {
            f = h + g;
        }
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public PathCell getParentPathCell() {
        return parentPathCell;
    }

    public void setParentPathCell(PathCell parentPathCell) {
        this.parentPathCell = parentPathCell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PathCell pathCell = (PathCell) o;

        return mIndex == pathCell.mIndex;

    }

    @Override
    public int hashCode() {
        return mIndex;
    }
}
