package chess.pieces;

import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(boolean available,int x, int y) {
        this.setAvailable(available);
        this.pieceLocationSpot.setLocation(x,y);
    }

    public synchronized Collection<Spot> getPossibleMoves() {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        int i=1;
        int j=1;
        Spot upRight = new Spot(new Spot.Location(pieceLocationSpot.location.getX()+i,pieceLocationSpot.location.getY()+j));
        while(upRight.available){
            upRight = new Spot(new Spot.Location(pieceLocationSpot.location.getX()+i,pieceLocationSpot.location.getY()+i)); //Spot do pierwszego dostępnego po przekątnej w prawo
            possibleMoves.add(upRight);
            i++;
            j++;
        }

        i=1;
        j=1;
        Spot upLeft = new Spot(new Spot.Location(pieceLocationSpot.location.getX()-i,pieceLocationSpot.location.getY()+j));
        while(upLeft.available){
            upLeft = new Spot(new Spot.Location(pieceLocationSpot.location.getX()-i,pieceLocationSpot.location.getY()+j)); //Spot do pierwszego dostępnego po przekątnej w prawo
            possibleMoves.add(upLeft);
            i++;
            j++;
        }

        i=1;
        j=1;
        Spot downRight = new Spot(new Spot.Location(pieceLocationSpot.location.getX()+i,pieceLocationSpot.location.getY()-j));
        while(downRight.available){
            downRight = new Spot(new Spot.Location(pieceLocationSpot.location.getX()+i,pieceLocationSpot.location.getY()-j)); //Spot do pierwszego dostępnego po przekątnej w prawo
            possibleMoves.add(downRight);
            i++;
            j++;
        }

        i=1;
        j=1;
        Spot downLewft = new Spot(new Spot.Location(pieceLocationSpot.location.getX()-i,pieceLocationSpot.location.getY()-j));
        while(downRight.available){
            downRight = new Spot(new Spot.Location(pieceLocationSpot.location.getX()-i,pieceLocationSpot.location.getY()+i)); //Spot do pierwszego dostępnego po przekątnej w prawo
            possibleMoves.add(downRight);
            i++;
            j++;
        }

        return possibleMoves;
    }

    public synchronized boolean validateMove(Spot destination) { //Sprawdzamy czy należy do zbioru
        return this.getPossibleMoves().contains(destination);
    }

    public synchronized void move(Spot destination){ //Wykonujemy ruch
        if(validateMove(destination)) this.pieceLocationSpot = destination;
    }
}
