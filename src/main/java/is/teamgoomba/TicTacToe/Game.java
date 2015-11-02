package is.teamgoomba.TicTacToe;

public class Game {
    Board board;
    int first;
    int player;
    private final int dim = 3;

    Game() {
        board = new Board();
        player = 1;
        first = 1;
    }

    public int getPlayer() {
        return player;
    }

    public String getBoard(){
        return board.getBoard();
    } 
    
    public int getWinner(){
        return board.getWinner(); 
    }
    
    public boolean gameOver(){
        return board.getWinner() != 0; 
    }
    
    public void restart(){
        board = new Board();
        if(first == 1){
            player = 2;
        }else{
            player = 1;
        }
        first = player;
    }
 
    public boolean move(int inp){
        if(inp < 0 || inp > 8) {
	    return false;
	} 
        int x = inp % dim;
        int y = inp / dim;
        boolean success = board.place(x,y,player);
        if(success){
            if(player == 1){
                this.player = 2;
                return true;
            }
            this.player = 1;
            return true;
        }
        return false;
    }
}

