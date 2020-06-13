package chess.pieces;

import chess.Config;
import chess.game.Spot;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("serial")
public class Bishop extends Piece {

    public BufferedImage getPieceIcon() {
        return Config.Instance().pieceImages[3];
    }

    public synchronized Collection<Spot> getPossibleMoves(Spot[][] board) {

        List<Spot> possibleMoves = new ArrayList<Spot>();
        int i = 1;
        int j = 1;

        Spot upRight;
        try {
            while ((upRight = board[getX() + i][getY() + j]).getPiece() == null) {
                possibleMoves.add(upRight);
                i++;
                j++;


            }
            if (upRight != null && upRight.getPiece() != null && upRight.getPiece().getColor() != getColor()) {
                possibleMoves.add(upRight);
            }
        } catch (Exception ignored) {
        }

        i = 1;
        j = 1;
        Spot upLeft;
        try {
            while ((upLeft = board[getX() + i][getY() - j]).getPiece() == null) {
                possibleMoves.add(upLeft);
                i++;
                j++;


            }
            if (upLeft != null && upLeft.getPiece() != null && upLeft.getPiece().getColor() != getColor()) {
                possibleMoves.add(upLeft);
            }
        } catch (Exception ignored) {
        }

        i = 1;
        j = 1;
        Spot downRight;
        try {
            while ((downRight = board[getX() - i][getY() + j]).getPiece() == null) {
                possibleMoves.add(downRight);
                i++;
                j++;


            }
            if (downRight != null && downRight.getPiece() != null && downRight.getPiece().getColor() != getColor()) {
                possibleMoves.add(downRight);
            }
        } catch (Exception ignored) {
        }

        i = 1;
        j = 1;
        Spot downLeft;
        try {
            while ((downLeft = board[getX() - i][getY() - j]).getPiece() == null) {
                possibleMoves.add(downLeft);
                i++;
                j++;

            }
            if (downLeft != null && downLeft.getPiece() != null && downLeft.getPiece().getColor() != getColor()) {
                possibleMoves.add(downLeft);
            }
        } catch (Exception ignored) {
        }

        return possibleMoves;
    }

    public synchronized Collection<Spot> getPossibleAttacks(Spot[][] board) {

        List<Spot> possibleMoves = new ArrayList<Spot>();
        int i = 1;
        int j = 1;

        Spot upRight;
        try {
            while ((upRight = board[getX() + i][getY() + j]).getPiece() == null && upRight != null) {
                possibleMoves.add(upRight);
                i++;
                j++;

            }
            if (upRight != null && upRight.getPiece() != null && upRight.getPiece().getColor() != getColor()) {
                possibleMoves.add(upRight);
            }

            if (upRight != null && upRight.getPiece() instanceof King && (upRight = board[getX() + i + 1][getY() + j + 1]).getPiece() == null && upRight != null) {
                possibleMoves.add(upRight);
            }
        } catch (Exception ignored) {
        }

        i = 1;
        j = 1;
        Spot upLeft;
        try {
            while ((upLeft = board[getX() + i][getY() - j]).getPiece() == null && upLeft != null) {
                possibleMoves.add(upLeft);
                i++;
                j++;

            }
            if (upLeft != null && upLeft.getPiece() != null && upLeft.getPiece().getColor() != getColor()) {
                possibleMoves.add(upLeft);
            }
            if (upLeft != null && upLeft.getPiece() instanceof King && (upLeft = board[getX() + i + 1][getY() - j - 1]).getPiece() == null && upLeft != null) {
                possibleMoves.add(upLeft);
            }
        } catch (Exception ignored) {
        }

        i = 1;
        j = 1;
        Spot downRight;
        try {
            while ((downRight = board[getX() - i][getY() + j]).getPiece() == null && downRight != null) {
                possibleMoves.add(downRight);
                i++;
                j++;

            }
            if (downRight != null && downRight.getPiece() != null && downRight.getPiece().getColor() != getColor()) {
                possibleMoves.add(downRight);
            }
            if (downRight != null && downRight.getPiece() instanceof King && (downRight = board[getX() - i - 1][getY() + j + 1]).getPiece() == null && downRight != null) {
                possibleMoves.add(downRight);
            }
        } catch (Exception ignored) {
        }

        i = 1;
        j = 1;
        Spot downLeft;
        try {
            while ((downLeft = board[getX() - i][getY() - j]).getPiece() == null && downLeft != null) {
                possibleMoves.add(downLeft);
                i++;
                j++;

            }
            if (downLeft != null && downLeft.getPiece() != null && downLeft.getPiece().getColor() != getColor()) {
                possibleMoves.add(downLeft);
            }
            if (downLeft != null && downLeft.getPiece() instanceof King && (downLeft = board[getX() - i - 1][getY() - j - 1]).getPiece() == null && downLeft != null) {
                possibleMoves.add(downLeft);
            }
        } catch (Exception ignored) {
        }

        return possibleMoves;
    }
}
