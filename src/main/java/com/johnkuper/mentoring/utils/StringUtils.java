package com.johnkuper.mentoring.utils;

import com.johnkuper.mentoring.map.MazeGenerator;

/**
 * Created by Dmitriy_Korobeinikov on 4/22/2015.
 */
public class StringUtils {
    public static String replaceChar(String string, int charIndex, char toChar) {
        return string.substring(0, charIndex) + toChar + string.substring(charIndex + 1);
    }

    public static String swapDuckChar(String map, int previousDuckIndex, int nextDuckIndex, char duckReplacer) {
        String replaceMap = replaceChar(map, previousDuckIndex, duckReplacer);
        replaceMap = replaceChar(replaceMap, nextDuckIndex, MazeGenerator.DUCK_CHAR);
        return replaceMap;
    }

    public static String getMapWithLineBrakes(String mazeMap) {
        StringBuilder sb = new StringBuilder();
        int columnCount = mazeMap.indexOf(MazeGenerator.CORNER_CHAR) + 1;
        for (int i = 0; i < mazeMap.length(); i++) {
            if (i > 0 && (i % columnCount == 0)) {
                sb.append("\n");
            }
            sb.append(mazeMap.charAt(i));
        }
        return sb.toString();
    }
}
