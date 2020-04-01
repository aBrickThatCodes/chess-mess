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

public class Rook extends Piece {

    public Rook(boolean available) {
        this.setAvailable(available);
    }

    @Override
<<<<<<< HEAD
    public synchronized Collection<Spot> getPossibleMoves() {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        int i=1;
        Spot ahead = new Spot(new Spot.Location(pieceLocationSpot.location.getX(),pieceLocationSpot.location.getY()+i));
        while(ahead.available){
            ahead = new Spot(new Spot.Location(pieceLocationSpot.location.getX(),pieceLocationSpot.location.getY()+i)); //Spot do pierwszego dostępnego przed pionkiem
            possibleMoves.add(ahead);
            i++;
        }

        i=1;
        Spot left = new Spot(new Spot.Location(pieceLocationSpot.location.getX()-i,pieceLocationSpot.location.getY()));
        while(left.available){
            left = new Spot(new Spot.Location(pieceLocationSpot.location.getX()-i,pieceLocationSpot.location.getY())); //Spot do pierwszego dostępnego na lewo
            possibleMoves.add(left);
            i++;
        }

        i=1;
        Spot right = new Spot(new Spot.Location(pieceLocationSpot.location.getX()+i,pieceLocationSpot.location.getY()));
        while(right.available){
            right = new Spot(new Spot.Location(pieceLocationSpot.location.getX()+i,pieceLocationSpot.location.getY())); //Spot do pierwszego dostępnego na prawo
            possibleMoves.add(right);
            i++;
        }

        i=1;
        Spot behind  = new Spot(new Spot.Location(pieceLocationSpot.location.getX(),pieceLocationSpot.location.getY()-i));
        while(behind.available){
            behind = new Spot(new Spot.Location(pieceLocationSpot.location.getX(),pieceLocationSpot.location.getY()-i)); //Spot do pierwszego dostępnego za
            possibleMoves.add(behind);
            i++;
        }

        return possibleMoves;
    }

    public synchronized boolean validateMove(Spot destination) { //Sprawdzamy czy należy do zbioru
        return this.getPossibleMoves().contains(destination);
    }

    public synchronized void move(Spot destination){ //Wykonujemy ruch
        if(validateMove(destination)) this.pieceLocationSpot = destination;
=======
    public Collection<Spot> getPossibleMoves() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean validateMove(Spot destination) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void move(Spot destination) {
        // TODO Auto-generated method stub

>>>>>>> 703f83e219a1d34673921e14826f642578ddab8f
    }
}
