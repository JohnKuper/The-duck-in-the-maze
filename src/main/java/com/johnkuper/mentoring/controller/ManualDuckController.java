package com.johnkuper.mentoring.controller;

import com.johnkuper.mentoring.model.Duck;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Dmitriy_Korobeinikov on 4/28/2015.
 */
public class ManualDuckController extends BaseDuckController implements KeyListener {

    public ManualDuckController(Duck duck) {
        super(duck);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        parseMove(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
