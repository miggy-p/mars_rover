import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Objects;

/**
 * | N S E W
 * - |---------
 * L | W E N S
 * R | E W S N
 * M | N S E W
 */

@Getter
@Setter
public class MarsRover {

    private HashMap<Character, Integer> directions;
    private HashMap<Character, Integer> moves;
    private char[][] matrix;
    private MarsRover.Position position;
    private int[][] space;
    MarsRover(){
        init();
    }



    /**
     * Command to move the rovers and change direction
     */
    public void executeCommand(char command) {
        char updateDirection = getUpdateDirection(command);
        int[] updatePosition;
        if (command == 'M') {
            updatePosition = getUpdateLocation(position.getR(), position.getC(), updateDirection);
            position.setR(updatePosition[0]);
            position.setC(updatePosition[1]);
        }
        position.setDir(updateDirection);
    }

    public char getUpdateDirection(char movement) {
        char updatedDir = matrix[moves.get(movement)][directions.get(position.getDir())];
        return updatedDir;
    }

    public int[] getUpdateLocation(int row, int col, char direction) throws IllegalStateException {
        switch (direction) {
            case 'N':
                row -= 1;
                break;
            case 'S':
                row += 1;
                break;
            case 'E':
                col += 1;
                break;
            case 'W':
                col -= 1;
                break;
        }
        if (row < 0 || col < 0 || row >= 5 || col >= 5)
            throw new IllegalStateException("rover can not move");
        int[] loc = new int[2];
        loc[0] = row;
        loc[1] = col;
        return loc;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    static
    class Position {
        int r;
        int c;
        char dir;

    }


    private void buildDirectionMatrix() {
        int col = directions.size();
        int row =  moves.size();
        matrix = new char[row][col];
        matrix[0][0] = 'W';
        matrix[0][1] = 'E';
        matrix[0][2] = 'N';
        matrix[0][3] = 'S';
        matrix[1][0] = 'E';
        matrix[2][1] = 'W';
        matrix[1][2] = 'S';
        matrix[1][3] = 'N';
        matrix[2][0] = 'N';
        matrix[2][1] = 'S';
        matrix[2][2] = 'E';
        matrix[2][3] = 'W';
    }


    /**
     * Helper function to build map move to a row in matrix
     */
    private void populateMoves() {
        moves = new HashMap<Character, Integer>();
        moves.put('L', 0);
        moves.put('R', 1);
        moves.put('M', 2);
    }

    /**
     * Helper function to build map direction to a col in matrix
     */
    private void populateDirections() {
        directions = new HashMap<Character, Integer>();
        directions.put('N', 0);
        directions.put('S', 1);
        directions.put('E', 2);
        directions.put('W', 3);
    }


    public Character getDirectionWithRespectToMoveAndDir(Character move , Character dir){
       return matrix[moves.get(move)][directions.get(dir)];
    }
    public Integer getDirectionWithLocation(Character loc){
        if(Objects.isNull(directions)){
            populateDirections();;
        }
        return directions.get(loc);
    }

    public Integer getMovesWithLocation(Character loc){
        if(Objects.isNull(moves)){
            populateMoves();
        }
        return moves.get(loc);
    }


    public void init(){
        populateMoves();
        populateDirections();
        buildDirectionMatrix();
    }



}