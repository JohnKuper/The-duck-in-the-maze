package com.johnkuper.mentoring.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dmitriy_Korobeinikov on 4/21/2015.
 */
public class MazeCell extends JLabel {
    public MazeCell(Color color) {
        setBackground(color);
        setOpaque(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(15, 15);
    }
}

