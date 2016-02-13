package com.johnkuper.mentoring.observer;

import com.johnkuper.mentoring.map.MazeGenerator;
import com.johnkuper.mentoring.model.DuckState;
import com.johnkuper.mentoring.ui.AlertDialog;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dmitriy_Korobeinikov on 4/23/2015.
 */
public class ExitObserver implements IObserver {

    @Override
    public void update(DuckState protocol) {
        if (protocol.standOn == MazeGenerator.EXIT_CHAR) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new AlertDialog(new JFrame(), "Congratulations", "You have successfully finished the maze!");
                    Frame[] frames = Frame.getFrames();
                    for (Frame frame : frames) {
                        frame.dispose();
                    }
                }
            });
        }
    }

    @Override
    public void setSubject(DuckSubject subject) {
    }
}
