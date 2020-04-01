package chess.pieces;

import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {

    public King(boolean available,int x,int y) {
        this.setAvailable(available);
        this.pieceLocationSpot.setLocation(x,y);
    }

    public synchronized Collection<Spot> getPossibleMoves() {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        int i=1;
        int j=1;
        Spot upRight = new Spot(new Spot.Location(pieceLocationSpot.location.getX()+i,pieceLocationSpot.location.getY()+j));
        if(upRight.available) possibleMoves.add(upRight);

        i=1;
        j=1;
        Spot upLeft = new Spot(new Spot.Location(pieceLocationSpot.location.getX()-i,pieceLocationSpot.location.getY()+j));
        if(upLeft.available) possibleMoves.add(upLeft);

        i=1;
        j=1;
        Spot downRight = new Spot(new Spot.Location(pieceLocationSpot.location.getX()+i,pieceLocationSpot.location.getY()-j));
        if(downRight.available) possibleMoves.add(downRight);

        i=1;
        j=1;
        Spot downLeft = new Spot(new Spot.Location(pieceLocationSpot.location.getX()-i,pieceLocationSpot.location.getY()-j));
        if(downLeft.available) possibleMoves.add(downLeft);

        i=1;
        Spot ahead = new Spot(new Spot.Location(pieceLocationSpot.location.getX(),pieceLocationSpot.location.getY()+i));
        if(ahead.available) possibleMoves.add(ahead);

        i=1;
        Spot left = new Spot(new Spot.Location(pieceLocationSpot.location.getX()-i,pieceLocationSpot.location.getY()));
        if(left.available) possibleMoves.add(left);

        i=1;
        Spot right = new Spot(new Spot.Location(pieceLocationSpot.location.getX()+i,pieceLocationSpot.location.getY()));
        if(right.available) possibleMoves.add(right);

        i=1;
        Spot behind  = new Spot(new Spot.Location(pieceLocationSpot.location.getX(),pieceLocationSpot.location.getY()-i));
        if(behind.available) possibleMoves.add(behind);

        return possibleMoves;
    }

    public synchronized boolean validateMove(Spot destination) { //Sprawdzamy czy należy do zbioru
        return this.getPossibleMoves().contains(destination);
    }

    public synchronized void move(Spot destination){ //Wykonujemy ruch
        if(validateMove(destination)) this.pieceLocationSpot = destination;
    }
}
