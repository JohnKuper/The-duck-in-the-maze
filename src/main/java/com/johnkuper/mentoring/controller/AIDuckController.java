package com.johnkuper.mentoring.controller;

import com.johnkuper.mentoring.map.PathFinder;
import com.johnkuper.mentoring.model.Duck;

import java.util.ArrayList;

/**
 * Created by Dmitriy_Korobeinikov on 4/27/2015.
 */
public class AIDuckController extends BaseDuckController {
    public AIDuckController(Duck duck) {
        super(duck);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    moveToExit();
                } catch (InterruptedException e) {
                    System.out.println("AI controller was interrupted");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void moveToExit() throws InterruptedException {
        PathFinder pathFinder = new PathFinder(mMap);
        ArrayList<Integer> movements = pathFinder.findPath();
        for (Integer movementCode : movements) {
            Thread.sleep(50);
            parseMove(movementCode);
        }
    }
}
