package chess.pieces;

public class Pawn extends Piece {

    public Pawn(boolean available,int x, int y){
        this.setAvailable(available);
        this.setX(x);
        this.setY(y);
    }

    public void move(int move, int direction){
        if (this.getY() == 1 || this.getY() == 6) {
            if (move > 2) {
                System.out.println("Wrong move");
            }
            else this.setY(this.getY() + move);
        }
        else{
            move = 1;
            this.setY(this.getY()+move);
        }
    }
}
