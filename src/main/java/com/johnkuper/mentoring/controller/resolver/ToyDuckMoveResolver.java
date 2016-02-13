package com.johnkuper.mentoring.controller.resolver;

import com.johnkuper.mentoring.map.MazeGenerator;
import com.johnkuper.mentoring.model.ToyDuck;

/**
 * Created by Dmitriy_Korobeinikov on 4/28/2015.
 */
public class ToyDuckMoveResolver implements DuckMoveResolver {

    private ToyDuck mDuck;

    public ToyDuckMoveResolver(ToyDuck duck) {
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
                mDuck.waveWings();
                break;
        }
    }
}
