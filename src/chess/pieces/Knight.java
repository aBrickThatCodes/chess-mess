package chess.pieces;

<<<<<<< HEAD
import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
=======
import java.util.Collection;

import chess.game.Spot;
>>>>>>> 703f83e219a1d34673921e14826f642578ddab8f

public class Knight extends Piece {

    public Knight(boolean available) {
        this.setAvailable(available);
    }

<<<<<<< HEAD
    public synchronized Collection<Spot> getPossibleMoves() {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        Spot upRight1 = new Spot(new Spot.Location(pieceLocationSpot.location.getX()+1,pieceLocationSpot.location.getY()+3));
        if(upRight1.available) possibleMoves.add(upRight1); //Skok wyższy w prawo góra

        Spot upRight2 = new Spot(new Spot.Location(pieceLocationSpot.location.getX()+3,pieceLocationSpot.location.getY()+1));
        if(upRight2.available) possibleMoves.add(upRight2); //skok niższy w prawo góra

        Spot upLeft1 = new Spot(new Spot.Location(pieceLocationSpot.location.getX()-1,pieceLocationSpot.location.getY()+3));
        if(upLeft1.available) possibleMoves.add(upLeft1); //Skok wyższy w lewo góra

        Spot upLeft2 = new Spot(new Spot.Location(pieceLocationSpot.location.getX()-3,pieceLocationSpot.location.getY()+1));
        if(upLeft2.available) possibleMoves.add(upLeft2); //skok niższy w lewogóra

        Spot downRight1 = new Spot(new Spot.Location(pieceLocationSpot.location.getX()+3,pieceLocationSpot.location.getY()-1));
        if(downRight1.available) possibleMoves.add(downRight1); //Skok wyższy w prawo dół

        Spot downRight2 = new Spot(new Spot.Location(pieceLocationSpot.location.getX()+1,pieceLocationSpot.location.getY()-3));
        if(downRight2.available) possibleMoves.add(downRight2); //skok niższy w prawo dół

        Spot downLeft1 = new Spot(new Spot.Location(pieceLocationSpot.location.getX()-3,pieceLocationSpot.location.getY()-1));
        if(downLeft1.available) possibleMoves.add(downLeft1); //Skok wyższy w prawo dół

        Spot downLeft2 = new Spot(new Spot.Location(pieceLocationSpot.location.getX()-1,pieceLocationSpot.location.getY()-3));
        if(downLeft2.available) possibleMoves.add(downLeft2); //skok niższy w prawo dół

        return possibleMoves;
    }

    public synchronized boolean validateMove(Spot destination) { //Sprawdzamy czy należy do zbioru
        return this.getPossibleMoves().contains(destination);
    }

    public synchronized void move(Spot destination){ //Wykonujemy ruch
        if(validateMove(destination)) this.pieceLocationSpot = destination;
=======
    @Override
    public Collection<Spot> getPossibleMoves() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean validateMove(Spot destination) {
        // TODO Auto-generated method stub
        return false;
>>>>>>> 703f83e219a1d34673921e14826f642578ddab8f
    }

    @Override
    public void move(Spot destination) {
        // TODO Auto-generated method stub

    }
    
}
