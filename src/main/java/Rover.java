import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Rover {

    public static final Integer N = 1;
    public static final Integer E = 2;
    public static final Integer S = 3;
    public static final Integer W = 4;

    public void initialize(int x , int y , Character direction){
        this.location = new Position(x , y);
        this.direction = direction;
    }

    Position location;
    Character direction;

    @ToString.Exclude
    Position maxPoint;

    @ToString.Exclude
    Position minPoint = new Position(0,0);

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    static
    class Position {
        Integer x ;
        Integer y ;
    }

    public void print() {
        System.out.println("Location is " + location.toString() + " with direction " + direction);
    }

    public void processCommands(String commands) {

        System.out.println(commands);
        char[] command = commands.toCharArray();

        for (int idx = 0; idx < commands.length(); idx++) {
            execute(command[idx]);
        }
    }

    private void execute(Character command) {
        switch (command){
            case 'L':
                turnLeft();
                break;
            case 'R' :
                turnRight();
                break;
            case 'M' :
                move();
                break;
            default:
                throw new IllegalArgumentException(String.format("Invalid Operation %s allowed [L,R,M]", command));
        }
    }

    private void move() {
        switch (direction){
            case 'N' :
                location.y++;
                break;
            case 'W' :
                location.x--;
                break;
            case 'S' :
                location.y--;
                break;
            case 'E' :
                location.x++;
                break;
        }

        if(maxPoint.x < location.x || location.x < minPoint.x || maxPoint.y < location.y || location.y < minPoint.y){
            throw new IllegalArgumentException("This state is not allowed");
        }
    }
    private void turnLeft() {
        switch (direction){
            case 'N':
                direction = 'W';
                break;
            case 'W':
                direction = 'S';
                break;
            case 'S':
                direction = 'E';
                break;
            case 'E':
                direction = 'N';
                break;
        }
    }

    private void turnRight() {
        switch (direction){
            case 'N':
                direction = 'E';
                break;
            case 'E':
                direction = 'S';
                break;
            case 'S':
                direction = 'W';
                break;
            case 'W':
                direction = 'N';
                break;
        }
    }
}
