package is.teamgoomba.TicTacToe;


public class Board {
    private final int dim = 3;
    int[][] Grid;

    Board(){
    	Grid = new int[][]{
        {0,0,0},
        {0,0,0},
        {0,0,0}};
 
    }

    String GetBoard(){
        StringBuilder build = new StringBuilder();
        for(int y = 0; y < dim; y++){
            for(int x = 0; x < dim; x++){
            	build.append(Grid[y][x]);
            }
        }
    	return build.toString();
    }
}

