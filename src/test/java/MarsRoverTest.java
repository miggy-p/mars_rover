import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;



public class MarsRoverTest {
    private MarsRover marsRover;
    MarsRover.Position position;
    int[][] space;


    @BeforeEach
    public void init() {
        marsRover = new MarsRover();
        space = new int[5][5];
        position = new MarsRover.Position(1, 2, 'N');
        marsRover.setPosition(position);
        marsRover.setSpace(space);
    }

    @Test
    public void populateMovesTest() {
        Assertions.assertEquals(Integer.valueOf(2), marsRover.getMovesWithLocation('M'));
    }

    @Test
    public void populateDirectionTest() {
        Assertions.assertEquals(Integer.valueOf(1), marsRover.getDirectionWithLocation('S'));
    }

    @Test
    public void matrixValueTest() {
        System.out.println(marsRover.getDirectionWithRespectToMoveAndDir('R', 'E'));
        Assertions.assertEquals('S', marsRover.getDirectionWithRespectToMoveAndDir('R', 'E'));
    }

    @Test
    public void matrixValueTestSecond() {
        Assertions.assertEquals('N', marsRover.getDirectionWithRespectToMoveAndDir('M', 'N'));
    }

    @Test
    public void getDirectionTest() {
        Assertions.assertEquals('W', marsRover.getUpdateDirection('L'));
        System.out.println("Current Direction: " + position.getDir());
    }

    @Test
    public void getUpdateLocationTest() {
        int[] res = marsRover.getUpdateLocation(2, 3, 'E');
        Assertions.assertEquals(4, res[1]);
    }

    @Test
    public void commandsTest() {
        char[] commands = new char[]{'L', 'M', 'L', 'M', 'L', 'M', 'L', 'M', 'M'};
        for (char c : commands) {
            marsRover.executeCommand(c);
        }
        System.out.println(position.getC());
        Assertions.assertEquals(0, position.getR());
        Assertions.assertEquals(2, position.getC());
    }

}
