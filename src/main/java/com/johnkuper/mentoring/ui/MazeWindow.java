package com.johnkuper.mentoring.ui;

import com.johnkuper.mentoring.controller.DuckController;
import com.johnkuper.mentoring.controller.ManualDuckController;
import com.johnkuper.mentoring.map.MapChangeListener;

import javax.swing.*;
import java.awt.*;

import static com.johnkuper.mentoring.map.MazeGenerator.*;

/**
 * Created by Dmitriy_Korobeinikov on 4/21/2015.
 */
public class MazeWindow extends JPanel implements MapChangeListener {

    public MazeWindow(String mazeMap, DuckController duckController) {
        setFocusable(true);
        requestFocusInWindow();
        if (duckController instanceof ManualDuckController) {
            ManualDuckController realDuckController = (ManualDuckController) duckController;
            addKeyListener(realDuckController);
        }
        int columnCount = mazeMap.indexOf(CORNER_CHAR) + 1;
        int row = mazeMap.length() / columnCount;
        setLayout(new GridLayout(row, columnCount));
        duckController.setMapChangeListener(this);
        createUI(mazeMap);
    }

    private void createUI(String mazeMap) {
        for (int i = 0; i < mazeMap.length(); i++) {
            char mapChar = mazeMap.charAt(i);
            Color color = parseMapChar(mapChar);
            add(new MazeCell(color));
        }
    }

    private void refreshMaze(String mazeMap) {
        Component[] components = getComponents();
        for (int i = 0; i < components.length; i++) {
            char mapChar = mazeMap.charAt(i);
            Color color = parseMapChar(mapChar);
            components[i].setBackground(color);
        }
    }

    private Color parseMapChar(char mapChar) {
        Color color = null;
        switch (mapChar) {
            case DUCK_CHAR:
                color = Color.RED;
                break;
            case EXIT_CHAR:
                color = Color.GREEN;
                break;
            case WALL_CHAR:
                color = Color.BLACK;
                break;
            case CORNER_CHAR:
                color = Color.BLACK;
                break;
            case PASSAGE_CHAR:
                color = Color.WHITE;
                break;
            case LAKE_CHAR:
                color = Color.BLUE;
                break;
            case ABYSS_CHAR:
                color = Color.ORANGE;
        }
        return color;
    }

    @Override
    public void onMapChange(String mazeMap) {
        refreshMaze(mazeMap);
    }
}
