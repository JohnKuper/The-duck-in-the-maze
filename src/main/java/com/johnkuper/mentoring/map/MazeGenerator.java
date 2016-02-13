
package com.johnkuper.mentoring.map;

import com.johnkuper.mentoring.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Implements the basic requirements of a rectangular maze generator.
 * Subclasses provide a specific generation algorithm.
 */
public abstract class MazeGenerator {
    /**
     * Represents DUCK on the map
     */
    public static final char DUCK_CHAR = 'D';
    /**
     * Represents EXIT on the map
     */
    public static final char EXIT_CHAR = 'E';
    /**
     * Represents WALL on the map
     */
    public static final char WALL_CHAR = 'W';
    /**
     * Represents PASSAGE on the map
     */
    public static final char PASSAGE_CHAR = ' ';
    /**
     * Represents CORNER on the map
     */
    public static final char CORNER_CHAR = '*';
    /**
     * Represents LAKE on the map
     */
    public static final char LAKE_CHAR = 'L';
    /**
     * Represents ABYSS on the map
     */
    public static final char ABYSS_CHAR = 'A';
    /**
     * Represents UP.
     */
    public static final int UP = 0;

    /**
     * Represents RIGHT.
     */
    public static final int RIGHT = 1;

    /**
     * Represents DOWN.
     */
    public static final int DOWN = 2;

    /**
     * Represents LEFT.
     */
    public static final int LEFT = 3;
    private int width;
    private int height;

    // Stores whether the walls exist or not

    private boolean[] horizWalls;
    private boolean[] vertWalls;

    /**
     * A convenience structure that represents one cell.  It contains a cell's
     * coordinates.
     */
    protected static class Cell {
        protected int x;
        protected int y;

        /**
         * Creates a new cell object having the given coordinates.
         *
         * @param x the cell's X-coordinate
         * @param y the cell's Y-coordinate
         */
        protected Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    /**
     * Create a new maze generator.  The height and width in cells is
     * specified.
     *
     * @param width the maze width, in cells
     * @param width the maze height, in cells
     * @throws IllegalArgumentException if either size non-positive.
     */
    protected MazeGenerator(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }

        this.width = width;
        this.height = height;

        // Create the walls

        horizWalls = new boolean[width * (height + 1)];
        vertWalls = new boolean[(width + 1) * height];

        reset();
    }

    /**
     * Resets the maze.
     */
    public final void reset() {
        // Fill the walls

        Arrays.fill(horizWalls, true);
        Arrays.fill(vertWalls, true);
    }

    /**
     * Generates the maze.  This first resets the maze by calling
     * {@link #reset()}.
     */
    public final void generate() {
        reset();
        generateMaze();
    }

    /**
     * Generates the maze using a specific algorithm.  Subclasses implement
     * this.
     */
    protected abstract void generateMaze();

    /**
     * Checks the direction, and throws an <code>IllegalArgumentException</code>
     * if it is invalid.
     *
     * @param direction the direction value to check
     * @throws IllegalArgumentException if the direction value is invalid.
     */
    private static void checkDirection(int direction) {
        switch (direction) {
            case UP:
            case RIGHT:
            case DOWN:
            case LEFT:
                break;
            default:
                throw new IllegalArgumentException("Bad direction: " + direction);
        }
    }

    /**
     * Checks that the given cell location is valid.
     *
     * @param x the cell's X-coordinate
     * @param y the cell's Y-coordinate
     * @throws IndexOutOfBoundsException if the coordinate is out of range.
     */
    protected void checkLocation(int x, int y) {
        if (x < 0 || width <= x) {
            throw new IndexOutOfBoundsException("X out of range: " + x);
        }
        if (y < 0 || height <= y) {
            throw new IndexOutOfBoundsException("Y out of range: " + y);
        }
    }

    /**
     * Carves a path in the given direction from the given cell.
     *
     * @param x         the starting cell's X-coordinate
     * @param y         the starting cell's Y-coordinate
     * @param direction the direction to carve
     * @return whether the wall existed and was removed.  If the wall was
     * already gone, then this returns <code>false</code>.
     * @throws IllegalArgumentException  if the direction value is invalid.
     * @throws IndexOutOfBoundsException if the coordinate is out of range.
     * @see #UP
     * @see #RIGHT
     * @see #DOWN
     * @see #LEFT
     */
    protected boolean carve(int x, int y, int direction) {
        // Check the arguments

        checkDirection(direction);
        checkLocation(x, y);

        int index = -1;
        boolean[] array = null;

        switch (direction) {
            case UP:
                index = y * width + x;
                array = horizWalls;
                break;
            case DOWN:
                index = (y + 1) * width + x;
                array = horizWalls;
                break;
            case LEFT:
                index = y * (width + 1) + x;
                array = vertWalls;
                break;
            case RIGHT:
                index = y * (width + 1) + (x + 1);
                array = vertWalls;
                break;
        }

        // Set the wall to 'false' and return what it was before

        boolean b = array[index];
        array[index] = false;
        return b;
    }

    /**
     * Gets the maze width, in cells.
     *
     * @return the maze width in cells.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the maze height, in cells.
     *
     * @return the maze height in cells.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Return the maze map as String.  The following characters are used for each part.
     * <ul>
     * <li>{@link #WALL_CHAR} for the walls</li>
     * <li>{@link #CORNER_CHAR}for the corner fillers</li>
     * <li>{@link #PASSAGE_CHAR}for the passage way</li>
     * <li>{@link #DUCK_CHAR}for the duck</li>
     * <li>{@link #EXIT_CHAR}for the exit</li>
     * <li>{@link #LAKE_CHAR}for the lakes</li>
     * <li>{@link #ABYSS_CHAR}for the abysses</li>
     * </ul>
     */
    public String getMap(boolean isComplex) {
        String mazeMap = "";
        for (int y = 0; y < height; y++) {
            // Print a row of horizontal walls

            int rowBase = y * width;
            for (int x = 0; x < width; x++) {
                mazeMap += WALL_CHAR;
                mazeMap += (horizWalls[rowBase + x] ? WALL_CHAR : PASSAGE_CHAR);
            }
            mazeMap += CORNER_CHAR;

            // Print a row of vertical walls

            rowBase = y * (width + 1);
            for (int x = 0; x < width; x++) {
                mazeMap += (vertWalls[rowBase + x] ? WALL_CHAR : PASSAGE_CHAR);
                mazeMap += PASSAGE_CHAR;
            }
            mazeMap += (vertWalls[rowBase + width] ? WALL_CHAR : PASSAGE_CHAR);
        }

        // Print the last row of horizontal walls
        int rowBase = height * width;
        for (int x = 0; x < width; x++) {
            mazeMap += CORNER_CHAR;
            mazeMap += (horizWalls[rowBase + x] ? WALL_CHAR : PASSAGE_CHAR);
        }
        mazeMap += CORNER_CHAR;
        mazeMap = StringUtils.replaceChar(mazeMap, mazeMap.indexOf(PASSAGE_CHAR), DUCK_CHAR);
        mazeMap = StringUtils.replaceChar(mazeMap, mazeMap.lastIndexOf(PASSAGE_CHAR), EXIT_CHAR);
        if (isComplex) {
            mazeMap = addObstaclesToMap(mazeMap, LAKE_CHAR);
            mazeMap = addObstaclesToMap(mazeMap, ABYSS_CHAR);
        }
        return mazeMap;
    }

    private String addObstaclesToMap(String mazeMap, char obstacleChar) {
        ArrayList<Integer> passages = findAllIndexesOfPassage(mazeMap);
        for (int i = 0; i < passages.size() / 20; i++) {
            mazeMap = StringUtils.replaceChar(mazeMap, passages.get(new Random().nextInt(passages.size())), obstacleChar);
        }
        return mazeMap;
    }

    private ArrayList<Integer> findAllIndexesOfPassage(String mazeMap) {
        ArrayList<Integer> passages = new ArrayList<>();
        int pasIndex = mazeMap.indexOf(PASSAGE_CHAR);
        while (pasIndex >= 0) {
            passages.add(pasIndex);
            pasIndex = mazeMap.indexOf(PASSAGE_CHAR, pasIndex + 1);
        }
        return passages;
    }
}
