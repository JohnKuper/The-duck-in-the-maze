package com.johnkuper.mentoring.map;

import com.johnkuper.mentoring.model.PathCell;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.johnkuper.mentoring.map.MazeGenerator.*;

/**
 * Created by Dmitriy_Korobeinikov on 4/28/2015.
 */
public class PathFinder {
    private ArrayList<PathCell> mOpenCellList;
    private ArrayList<PathCell> mCloseCellList;
    private int[] mDirections;
    private int mColumnCount;
    private String mMazeMap;
    private boolean isExitFound;
    private PathCell mExitCell;

    public PathFinder(String mazeMap) {
        mOpenCellList = new ArrayList<PathCell>() {
            @Override
            public boolean add(final PathCell cell) {
                super.add(cell);
                Collections.sort(mOpenCellList, new Comparator<PathCell>() {
                    @Override
                    public int compare(PathCell cellOne, PathCell cellTwo) {
                        return cellOne.getF() - cellTwo.getF();
                    }
                });
                return true;
            }
        };
        mCloseCellList = new ArrayList<>();
        mMazeMap = mazeMap;
        mColumnCount = mazeMap.indexOf(CORNER_CHAR) + 1;
        mDirections = new int[]{-1, 1, -mColumnCount, mColumnCount};
    }

    public ArrayList<Integer> findPath() {
        PathCell duckCell = new PathCell();
        int duckIndex = mMazeMap.indexOf(DUCK_CHAR);
        duckCell.setIndex(duckIndex);
        mOpenCellList.add(duckCell);
        while (!isExitFound) {
            exploreMaze(mOpenCellList.get(0));
            if (mOpenCellList.isEmpty()) {
                System.out.println("THERE IS NO EXIT!");
            }
        }
        boolean isStartReached = false;
        ArrayList<Integer> indexForMovements = new ArrayList<>();
        indexForMovements.add(mExitCell.getIndex());
        while (!isStartReached) {
            PathCell parentCell = mExitCell.getParentPathCell();
            indexForMovements.add(parentCell.getIndex());
            mExitCell = parentCell;
            if (duckIndex == mExitCell.getIndex()) {
                isStartReached = true;
            }
        }
        return getMovements(indexForMovements);
    }

    private ArrayList<PathCell> exploreMaze(PathCell parentCell) {
        ArrayList<PathCell> childCells = new ArrayList<>();
        for (int direction : mDirections) {
            int childIndex = parentCell.getIndex() + direction;
            char mapChar = mMazeMap.charAt(childIndex);
            if (mapChar != WALL_CHAR && mapChar != CORNER_CHAR) {
                PathCell childCell = new PathCell();
                childCell.setIndex(childIndex);
                childCell.setParentPathCell(parentCell);
                childCell.setG(parentCell.getG() + 10);
                childCell.setH(getHForCell(childIndex));
                addCellToOpenList(childCell);
                deleteCellFromOpenList(parentCell);
                if (mapChar == EXIT_CHAR) {
                    isExitFound = true;
                    mExitCell = childCell;
                }
            }
        }
        return childCells;
    }

    private ArrayList<Integer> getMovements(ArrayList<Integer> indexForMovements) {
        ArrayList<Integer> movements = new ArrayList<>();
        for (int i = indexForMovements.size() - 1; i > 0; i--) {
            int firstIndex = indexForMovements.get(i);
            int nextIndex = indexForMovements.get(i - 1);
            if (nextIndex - firstIndex == 1) {
                movements.add(KeyEvent.VK_RIGHT);
            } else if (nextIndex - firstIndex == -1) {
                movements.add(KeyEvent.VK_LEFT);
            } else if (nextIndex - firstIndex == mColumnCount) {
                movements.add(KeyEvent.VK_DOWN);
            } else if (nextIndex - firstIndex == -mColumnCount) {
                movements.add(KeyEvent.VK_UP);
            }
        }
        return movements;
    }

    private int getHForCell(int index) {
        int moveToRight = 0;
        while (index % mColumnCount < mColumnCount - 1) {
            moveToRight++;
            index += 1;
        }
        int moveToDown = 0;
        while (mMazeMap.charAt(index - 1) != EXIT_CHAR) {
            moveToDown++;
            index += mColumnCount;
        }
        return (moveToRight + moveToDown) * 10;
    }

    private void addCellToOpenList(PathCell cell) {
        if (!mOpenCellList.contains(cell)) {
            mOpenCellList.add(cell);
        }
    }

    private void deleteCellFromOpenList(PathCell cell) {
        if (mOpenCellList.contains(cell)) {
            mOpenCellList.remove(cell);
        }
        if (!mCloseCellList.contains(cell)) {
            mCloseCellList.add(cell);
        }
    }
}
