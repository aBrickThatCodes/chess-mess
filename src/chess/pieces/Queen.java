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

public class Queen extends Piece {

    public Queen(boolean available) {
        this.setAvailable(available);
    }

<<<<<<< HEAD
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

        i=1;
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
    }

    @Override
    public void move(Spot destination) {
        // TODO Auto-generated method stub
>>>>>>> 703f83e219a1d34673921e14826f642578ddab8f

    public synchronized void move(Spot destination){ //Wykonujemy ruch
        if(validateMove(destination)) this.pieceLocationSpot = destination;
    }

}
