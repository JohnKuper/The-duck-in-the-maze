package com.johnkuper.mentoring.main;

import com.johnkuper.mentoring.controller.DuckController;
import com.johnkuper.mentoring.map.MazeGenerator;
import com.johnkuper.mentoring.map.RecursiveBacktrackerMazeGenerator;
import com.johnkuper.mentoring.model.Duck;
import com.johnkuper.mentoring.model.RealDuck;
import com.johnkuper.mentoring.ui.MazeWindow;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Dmitriy_Korobeinikov on 4/21/2015.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        PropertiesParser reader = new PropertiesParser();
        reader.parseProperties();
        Duck duck = reader.getDuck();
        DuckController controller = reader.getDuckController();
        int mazeSize = reader.getMazeSize();
        boolean isComplex = duck instanceof RealDuck;

        MazeGenerator generator = new RecursiveBacktrackerMazeGenerator(mazeSize, mazeSize);
        generator.generate();
        String mazeMap = generator.getMap(isComplex);
        controller.setMazeMap(mazeMap);
        createUI(mazeMap, controller);
    }

    public static void createUI(final String mazeMap, final DuckController controller) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("The duck in maze");
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.add(new MazeWindow(mazeMap, controller));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}