package com.johnkuper.mentoring.controller.resolver;

import com.johnkuper.mentoring.map.MazeGenerator;
import com.johnkuper.mentoring.model.RealDuck;

/**
 * Created by Dmitriy_Korobeinikov on 4/28/2015.
 */
public class RealDuckMoveResolver implements DuckMoveResolver {
    private RealDuck mDuck;

    public RealDuckMoveResolver(RealDuck duck) {
        mDuck = duck;
    }

    @Override
    public void resolveMove(char toMoveOn) {
        switch (toMoveOn) {
            case MazeGenerator.PASSAGE_CHAR:
                mDuck.walk(toMoveOn);
                break;
            case MazeGenerator.EXIT_CHAR:
                mDuck.walk(toMoveOn);
                mDuck.quack();
                break;
            case MazeGenerator.LAKE_CHAR:
                mDuck.swim(toMoveOn);
                mDuck.drink();
                break;
            case MazeGenerator.ABYSS_CHAR:
                mDuck.fly(toMoveOn);
                break;
        }
    }
}
