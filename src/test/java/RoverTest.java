import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverTest {

    private Rover rover;
    private Rover.Position maxPoint;

    public void init() {
        rover = new Rover();
        maxPoint = new Rover.Position(5,5);
        rover.setMaxPoint(maxPoint);
    }


    @Test
    public void testLeft() {
        System.out.println("Start test left");
        init();
        rover.initialize(1,2,'N');
        String commands = "L";
        rover.processCommands(commands);
        rover.print();
        Assertions.assertEquals('W', rover.getDirection());
        rover.processCommands(commands);
        rover.print();
        Assertions.assertEquals('S', rover.getDirection());
        rover.processCommands(commands);
        rover.print();
        Assertions.assertEquals('E', rover.getDirection());
        rover.processCommands(commands);
        rover.print();
        Assertions.assertEquals('N', rover.getDirection());
        System.out.println("Exit test left");
    }

    @Test
    public void testRight() {
        System.out.println("Start test right");
        init();
        rover.initialize(1,2,'N');
        String commands = "R";
        rover.processCommands(commands);
        rover.print();
        Assertions.assertEquals('E', rover.getDirection());
        rover.processCommands(commands);
        rover.print();
        Assertions.assertEquals('S', rover.getDirection());
        rover.processCommands(commands);
        rover.print();
        Assertions.assertEquals('W', rover.getDirection());
        rover.processCommands(commands);
        rover.print();
        Assertions.assertEquals('N', rover.getDirection());
        System.out.println("Exit test right");
    }

    @Test
    public void testMove() {
        System.out.println("Start test move");
        init();
        rover.initialize(1,2,'N');
        String commands = "M";
        rover.processCommands(commands);
        rover.print();
        Assertions.assertEquals(1, rover.getLocation().getX());
        Assertions.assertEquals(3, rover.getLocation().getY());
        commands = "LM";
        rover.processCommands(commands);
        rover.print();
        Assertions.assertEquals(0, rover.getLocation().getX());
        Assertions.assertEquals(3, rover.getLocation().getY());
        rover.processCommands(commands);
        rover.print();
        Assertions.assertEquals(0, rover.getLocation().getX());
        Assertions.assertEquals(2, rover.getLocation().getY());
        rover.processCommands(commands);
        rover.print();
        Assertions.assertEquals(1, rover.getLocation().getX());
        Assertions.assertEquals(2, rover.getLocation().getY());
        rover.processCommands(commands);
        rover.print();
        Assertions.assertEquals(1, rover.getLocation().getX());
        Assertions.assertEquals(3, rover.getLocation().getY());
        System.out.println("Exit test move");
    }


    @Test
    public void testCommandOne() {
        System.out.println("Start test command one");
        init();
        rover.initialize(1,2,'N');
        String commands = "LMLMLMLMM";
        rover.print();
        rover.processCommands(commands);
        rover.print();
        //1 3 N
        Assertions.assertEquals(1, rover.getLocation().getX());
        Assertions.assertEquals(3, rover.getLocation().getY());
        Assertions.assertEquals('N', rover.getDirection());
        System.out.println("Exit test command one");
    }

    @Test
    public void testCommandTwo() {
        System.out.println("Start test command two");
        init();
        rover.initialize(3,3,'E');
        String commands = "MMRMMRMRRM";
        rover.print();
        rover.processCommands(commands);
        rover.print();
        //5 1 E
        Assertions.assertEquals(5, rover.getLocation().getX());
        Assertions.assertEquals(1, rover.getLocation().getY());
        Assertions.assertEquals('E', rover.getDirection());
        System.out.println("Exit test command two");
    }

    @Test
    public void testCommandThree() {
        System.out.println("Start test illegalArgument");
        init();
        rover.initialize(1,1,'N');
        String commands = "LMM";
        rover.print();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> rover.processCommands(commands));
        rover.print();
        Assertions.assertNotNull(exception);
        System.out.println("Exit test IllegalArgument");
    }

}
