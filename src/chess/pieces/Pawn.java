package chess.pieces;

import chess.game.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {


    public Pawn(boolean available,int x, int y){
        this.setAvailable(available);
        this.pieceLocationSpot.setLocation(x,y);
    }

    //Zbiór możliwych ruchów
    public Collection<Spot> getPossibleMoves() {

        List<Spot> possibleMoves = new ArrayList<Spot>();

        Spot ahead = new Spot(new Spot.Location(pieceLocationSpot.location.getX(),pieceLocationSpot.location.getY()+1)); //Spot przeciwko
        if (ahead.available) possibleMoves.add(ahead); //Sprawdzamy dostępność i dodajemy do listy możliwych

        Spot aheadLeft = new Spot(new Spot.Location(pieceLocationSpot.location.getX()-1,pieceLocationSpot.location.getY()+1)); //Spot po lew
        if (!aheadLeft.available) possibleMoves.add(ahead);

        Spot aheadRight = new Spot(new Spot.Location(pieceLocationSpot.location.getX()+1,pieceLocationSpot.location.getY()+1)); //Spot po prawo
        if (!aheadRight.available) possibleMoves.add(ahead);

        return possibleMoves;
    }

    public boolean validateMove(Spot destination) { //Sprawdzamy czy należy do zbioru
        return this.getPossibleMoves().contains(destination);
    }

    public void move(Spot destination){ //Wykonujemy ruch
        if(validateMove(destination)) this.pieceLocationSpot = destination;
    }
}
