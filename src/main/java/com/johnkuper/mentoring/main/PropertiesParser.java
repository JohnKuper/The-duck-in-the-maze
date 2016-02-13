package com.johnkuper.mentoring.main;

import com.johnkuper.mentoring.controller.DuckController;
import com.johnkuper.mentoring.factory.ControllerFactory;
import com.johnkuper.mentoring.factory.ControllerTypes;
import com.johnkuper.mentoring.factory.DuckFactory;
import com.johnkuper.mentoring.factory.DuckTypes;
import com.johnkuper.mentoring.model.Duck;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Dmitriy_Korobeinikov on 4/28/2015.
 */
public class PropertiesParser {
    private static final String DUCK_TYPE = "DUCK_TYPE";
    private static final String DUCK_CONTROLLER = "DUCK_CONTROLLER";
    private static final String MAZE_SIZE = "MAZE_SIZE";

    private Duck mDuck;
    private DuckController mDuckController;
    private int mMazeSize;

    public void parseProperties() throws IOException {
        Properties properties = new Properties();
        String fileName = "maze.properties";

        InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (stream != null) {
            properties.load(stream);
        }

        String duckType = properties.getProperty(DUCK_TYPE);
        String duckControllerType = properties.getProperty(DUCK_CONTROLLER);
        String mazeSize = properties.getProperty(MAZE_SIZE);

        mDuck = DuckFactory.getDuck(DuckTypes.findByName(duckType));
        mDuckController = ControllerFactory.getController(ControllerTypes.findByName(duckControllerType), mDuck);
        mMazeSize = parseMazeSize(mazeSize);
    }

    private int parseMazeSize(String mazeSize) {
        switch (mazeSize) {
            case "small":
                return 10;
            case "medium":
                return 20;
            case "large":
                return 30;
            default:
                throw new IllegalArgumentException("Unsupported maze size: " + mazeSize);
        }
    }

    public Duck getDuck() {
        return mDuck;
    }

    public DuckController getDuckController() {
        return mDuckController;
    }

    public int getMazeSize() {
        return mMazeSize;
    }
}
