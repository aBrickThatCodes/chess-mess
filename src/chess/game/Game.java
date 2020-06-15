package chess.game;

import chess.Config;
import chess.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@SuppressWarnings({"serial", "unused"})
public class Game extends JFrame {

    GameData gameData = new GameData();

    public Game() {

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(640, 640);

        Player.AttackDirection[] values = Player.AttackDirection.values();

        if (Config.Instance().pvp) {
            for (int i = 0; i < Config.Instance().playerAmount; i++) {
                gameData.getPlayers().add(new Player.HumanPlayer(0, values[i % Config.Instance().playerAmount], Config.Instance().colors[i]));
                gameData.getPlayers().get(i).attackDirection = Player.AttackDirection.values()[i % 4];
            }
        } else {
            gameData.getPlayers().add(new Player.HumanPlayer(0, Player.AttackDirection.RIGHT, Config.Instance().colors[0]));
            for (int i = 1; i < Config.Instance().playerAmount; i++) {
                gameData.getPlayers().add(new Player.AIPlayer(0, values[i % Config.Instance().playerAmount], Config.Instance().colors[i]));
            }
        }

        gameData.getBoard().setBoard(gameData.getPlayers());

        for (int i = 0; i < Config.Instance().boardWidth; i++) {
            for (int j = 0; j < Config.Instance().boardHeight; j++) {
                gameData.getBoard().getBoard()[i][j].addMouseListener(new MyMouseListener(i, j));
            }
        }

        gameData.setCurrentTurn(gameData.getPlayers().get(0));
        this.add(gameData.getBoard());

        MusicThread musicThread = new MusicThread(this);
        musicThread.start();
    }

    public class MyMouseListener implements MouseListener {
        private int x;
        private int y;

        public MyMouseListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

            gameData.setCurrentTurn(gameData.getPlayers().get(gameData.getPlayerNum() % gameData.getPlayers().size()));

            if (gameData.getCurrentTurn() instanceof Player.HumanPlayer) {
                //Zwykły gracz
                int previusX = gameData.getCurrentX();
                gameData.setCurrentX(x);

                int previusY = gameData.getCurrentY();
                gameData.setCurrentY(y);

                //Opuszczenie pionka
                if (gameData.getCurrentChosenPiece() != null) {
                    if (gameData.getCurrentChosenPiece() instanceof King) {
                        King king = (King) gameData.getCurrentChosenPiece();
                        if (king.move(gameData.getCurrentX(), gameData.getCurrentY(), gameData.getBoard().getBoard(), mayBeChecked(king))) {

                            //Pojedynki
                            if (Config.Instance().duels && gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() != null && !(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() instanceof King)) {
                                Piece oldPiece = gameData.getCurrentChosenPiece();
                                Piece winner = DuelPane.duel(gameData.getCurrentChosenPiece(), gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece());
                                gameData.setCurrentChosenPiece(winner);
                                if (gameData.getCurrentChosenPiece() != oldPiece) {
                                    searchAndDestroy(oldPiece, gameData.getCurrentTurn());
                                }
                                gameData.getCurrentChosenPiece().setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                                gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                            }
                            //Teleportacja
                            if (gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].isTeleporting) {
                                Spot spot = gameData.getBoard().randomFreeSpot();
                                spot.setPiece(gameData.getCurrentChosenPiece());
                                gameData.currentChosenPiece.setLocation(spot.getX(), spot.getY());
                            } else {
                                king.setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                                gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(king);
                            }

                            //Przedmioty
                    if (Config.Instance().items) {
                        useItem(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()]);
                        gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                    }
                            //Usuwanie pionka przy zbiciu
                            if (gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() != null) {
                                searchAndDestroy(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece());
                            }
                            gameData.setCurrentChosenPiece(null);
                            System.out.println("Pionek przestawiono " + gameData.getCurrentY() + " " + gameData.getCurrentY());
                            gameData.setPlayerNum(gameData.getPlayerNum() + 1);
                        } else {
                            gameData.setCurrentChosenPiece(null);
                        }
                    } else if (gameData.getCurrentChosenPiece() instanceof Pawn) {
                        Pawn pawn = (Pawn) gameData.getCurrentChosenPiece();
                        if (pawn.move(gameData.getCurrentX(), gameData.getCurrentY(), gameData.getBoard().getBoard())) {
                            //Czyszczenie poprzedniego pola
                            gameData.getBoard().getBoard()[previusX][previusY].setPiece(null);

                            //Pojedynki
                            if (Config.Instance().duels && gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() != null && !(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() instanceof King)) {
                                Piece oldPiece = gameData.getCurrentChosenPiece();
                                Piece winner = DuelPane.duel(gameData.getCurrentChosenPiece(), gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece());
                                gameData.setCurrentChosenPiece(winner);
                                if (gameData.getCurrentChosenPiece() != oldPiece) {
                                    searchAndDestroy(oldPiece, gameData.getCurrentTurn());
                                }
                                gameData.getCurrentChosenPiece().setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                                gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                            }
                            //Teleportacja
                            if (gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].isTeleporting) {
                                Spot spot = gameData.getBoard().randomFreeSpot();
                                spot.setPiece(gameData.getCurrentChosenPiece());
                                gameData.currentChosenPiece.setLocation(spot.getX(), spot.getY());
                            } else {

                                pawn.setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                                gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(pawn);
                            }
                            //Przedmioty
                    if (Config.Instance().items) {
                        useItem(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()]);
                        gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                    }


                            //Usuwanie pionka przy zbiciu
                            if (gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() != null) {
                                searchAndDestroy(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece());
                            }
                            gameData.setCurrentChosenPiece(null);
                            System.out.println("Pionek przestawiono " + gameData.getCurrentY() + " " + gameData.getCurrentY());
                            gameData.setPlayerNum(gameData.getPlayerNum() + 1);
                        } else {
                            gameData.setCurrentChosenPiece(null);
                        }
                    } else if (gameData.getCurrentChosenPiece().move(gameData.getCurrentX(), gameData.getCurrentY(), gameData.getBoard().getBoard())) {
                        //Czyszczenie poprzedniego pola
                        gameData.getBoard().getBoard()[previusX][previusY].setPiece(null);


                        //Pojedynki
                        if (Config.Instance().duels && gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() != null && !(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() instanceof King)) {
                            Piece oldPiece = gameData.getCurrentChosenPiece();
                            Piece winner = DuelPane.duel(gameData.getCurrentChosenPiece(), gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece());
                            gameData.setCurrentChosenPiece(winner);
                            if (gameData.getCurrentChosenPiece() != oldPiece) {
                                searchAndDestroy(oldPiece, gameData.getCurrentTurn());
                            }
                            gameData.getCurrentChosenPiece().setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                            gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                        }
                        //Teleportacja
                        if (gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].isTeleporting) {
                            Spot spot = gameData.getBoard().randomFreeSpot();
                            spot.setPiece(gameData.getCurrentChosenPiece());
                            gameData.currentChosenPiece.setLocation(spot.getX(), spot.getY());
                        } else {
                            gameData.getCurrentChosenPiece().setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                            gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                        }

                        //Przedmioty
                    if (Config.Instance().items) {
                        useItem(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()]);
                        gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                    }
                        //Usuwanie pionka przy zbiciu
                        if (gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() != null) {
                            searchAndDestroy(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece());
                        }
                        gameData.setCurrentChosenPiece(null);
                        System.out.println("Pionek przestawiono " + gameData.getCurrentY() + " " + gameData.getCurrentY());
                        gameData.setPlayerNum(gameData.getPlayerNum() + 1);
                    } else if (!gameData.getCurrentChosenPiece().move(gameData.getCurrentX(), gameData.getCurrentY(), gameData.getBoard().getBoard())) {
                        System.out.println("Poza możliwościami pionka lub jest tam inny pionek");
                        gameData.setCurrentChosenPiece(null);
                    }
                    gameData.getBoard().refreshBoard();
                    gameData.getBoard().repaintColors();
                    setCheck();
                    isMate();
                    //Podniesienie pionka
                } else {
                    try {
                        gameData.setCurrentChosenPiece(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece());
                        if (validateChoosenPiece() && gameData.getCurrentChosenPiece() instanceof King) {
                            King king = (King) gameData.getCurrentChosenPiece();
                            for (Spot s : king.getPossibleMoves(gameData.getBoard().getBoard(), mayBeChecked(king))) {
                                if (gameData.getBoard().getBoard()[s.getX()][s.getY()].getColor() == Color.WHITE) {
                                    gameData.getBoard().getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                                } else {
                                    gameData.getBoard().getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                                }
                            }
                            System.out.println("Current spot " + gameData.getCurrentY() + " " + gameData.getCurrentY());
                            System.out.println("Previous spot " + previusX + " " + previusY);
                        } else if (validateChoosenPiece()) {
                            for (Spot s : gameData.getCurrentChosenPiece().getPossibleMoves(gameData.getBoard().getBoard())) {
                                if (gameData.getBoard().getBoard()[s.getX()][s.getY()].getColor() == Color.WHITE) {
                                    gameData.getBoard().getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                                } else {
                                    gameData.getBoard().getBoard()[s.getX()][s.getY()].setColor(Color.blue);
                                }
                            }
                            System.out.println("Current spot " + gameData.getCurrentY() + " " + gameData.getCurrentY());
                            System.out.println("Previous spot " + previusX + " " + previusY);
                        } else {
                            gameData.setCurrentChosenPiece(null);
                            System.out.println("Pionek przeciwnika lub puste pole");
                        }
                    } catch (Exception e) {
                        System.out.println("Brak pionka");
                    }
                }
                //Sztuczna inteligencja
            } else if (gameData.getCurrentTurn() instanceof Player.AIPlayer) {

                ArrayList<Piece> possiblePieces = new ArrayList<>();
                ArrayList<Spot> possibleMoves;

                for (ArrayList<Piece> pieces : gameData.getCurrentTurn().playerPieces) {
                    possiblePieces.addAll(pieces);
                }

                Spot choosenMove;

                for (Piece p : possiblePieces) {
                    gameData.setCurrentChosenPiece(possiblePieces.get((int) (possiblePieces.size() * Math.random())));
                    possibleMoves = (ArrayList<Spot>) gameData.getCurrentChosenPiece().getPossibleMoves(gameData.getBoard().getBoard());
                    if (possibleMoves.size() != 0) {
                        choosenMove = possibleMoves.get((int) ((possibleMoves.size() - 1) * Math.random()));
                        gameData.setCurrentX(choosenMove.getX());
                        gameData.setCurrentY(choosenMove.getY());
                        break;
                    }
                }

                int previusX = gameData.getCurrentChosenPiece().getX();
                int previusY = gameData.getCurrentChosenPiece().getY();

                if (gameData.getCurrentChosenPiece() instanceof King) {
                    King king = (King) gameData.getCurrentChosenPiece();
                    if (king.move(gameData.getCurrentX(), gameData.getCurrentY(), gameData.getBoard().getBoard(), mayBeChecked(king))) {

                        //Czyszczenie poprzedniego pola
                        gameData.getBoard().getBoard()[previusX][previusY].setPiece(null);

                        //Pojedynki
                        if (Config.Instance().duels && gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() != null && !(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() instanceof King)) {
                            Piece oldPiece = gameData.getCurrentChosenPiece();
                            Piece winner = DuelPane.duel(gameData.getCurrentChosenPiece(), gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece());
                            gameData.setCurrentChosenPiece(winner);
                            if (gameData.getCurrentChosenPiece() != oldPiece) {
                                searchAndDestroy(oldPiece, gameData.getCurrentTurn());
                            }
                            gameData.getCurrentChosenPiece().setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                            gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                        }
                        //Teleportacja
                        if (gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].isTeleporting) {
                            Spot spot = gameData.getBoard().randomFreeSpot();
                            spot.setPiece(gameData.getCurrentChosenPiece());
                            gameData.currentChosenPiece.setLocation(spot.getX(), spot.getY());
                        } else {
                            king.setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                            gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(king);
                        }

                        //Przedmioty
                    if (Config.Instance().items) {
                        useItem(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()]);
                        gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                    }
                        //Usuwanie pionka przy zbiciu
                        if (gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() != null) {
                            searchAndDestroy(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece());
                        }
                        gameData.setCurrentChosenPiece(null);
                        System.out.println("Pionek przestawiono " + gameData.getCurrentY() + " " + gameData.getCurrentY());
                        gameData.setPlayerNum(gameData.getPlayerNum() + 1);
                    } else {
                        gameData.setCurrentChosenPiece(null);
                    }
                } else if (gameData.getCurrentChosenPiece() instanceof Pawn) {
                    Pawn pawn = (Pawn) gameData.getCurrentChosenPiece();
                    if (pawn.move(gameData.getCurrentX(), gameData.getCurrentY(), gameData.getBoard().getBoard())) {

                        //Czyszczenie poprzedniego pola
                        gameData.getBoard().getBoard()[previusX][previusY].setPiece(null);


                        //Pojedynki
                        if (Config.Instance().duels && gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() != null && !(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() instanceof King)) {
                            Piece oldPiece = gameData.getCurrentChosenPiece();
                            Piece winner = DuelPane.duel(gameData.getCurrentChosenPiece(), gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece());
                            gameData.setCurrentChosenPiece(winner);
                            if (gameData.getCurrentChosenPiece() != oldPiece) {
                                searchAndDestroy(oldPiece, gameData.getCurrentTurn());
                            }
                            gameData.getCurrentChosenPiece().setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                            gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                        }
                        //Teleportacja
                        if (gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].isTeleporting) {
                            Spot spot = gameData.getBoard().randomFreeSpot();
                            spot.setPiece(gameData.getCurrentChosenPiece());
                            gameData.currentChosenPiece.setLocation(spot.getX(), spot.getY());
                        } else {
                            pawn.setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                            gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(pawn);
                        }

                        //Przedmioty
                    if (Config.Instance().items) {
                        useItem(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()]);
                        gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                    }
                        //Usuwanie pionka przy zbiciu
                        if (gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() != null) {
                            searchAndDestroy(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece());
                        }

                        gameData.setCurrentChosenPiece(null);
                        System.out.println("Pionek przestawiono " + gameData.getCurrentY() + " " + gameData.getCurrentY());
                        gameData.setPlayerNum(gameData.getPlayerNum() + 1);
                    } else {
                        gameData.setCurrentChosenPiece(null);
                    }
                } else if (gameData.getCurrentChosenPiece().move(gameData.getCurrentX(), gameData.getCurrentY(), gameData.getBoard().getBoard())) {
                    //Czyszczenie poprzedniego pola
                    gameData.getBoard().getBoard()[previusX][previusY].setPiece(null);


                    //Pojedynki
                    if (Config.Instance().duels && gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() != null && !(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() instanceof King)) {
                        Piece oldPiece = gameData.getCurrentChosenPiece();
                        Piece winner = DuelPane.duel(gameData.getCurrentChosenPiece(), gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece());
                        gameData.setCurrentChosenPiece(winner);
                        if (gameData.getCurrentChosenPiece() != oldPiece) {
                            searchAndDestroy(oldPiece, gameData.getCurrentTurn());
                        }
                        gameData.getCurrentChosenPiece().setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                        gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                    }
                    //Teleportacja
                    if (gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].isTeleporting) {
                        Spot spot = gameData.getBoard().randomFreeSpot();
                        spot.setPiece(gameData.getCurrentChosenPiece());
                        gameData.currentChosenPiece.setLocation(spot.getX(), spot.getY());
                    } else {
                        gameData.getCurrentChosenPiece().setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                        gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                    }

                    //Przedmioty
                    if (Config.Instance().items) {
                        useItem(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()]);
                        gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                    }
                    //Usuwanie pionka przy zbiciu
                    if (gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece() != null) {
                        searchAndDestroy(gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].getPiece());
                    }

                    gameData.setCurrentChosenPiece(null);
                    System.out.println("Pionek przestawiono " + gameData.getCurrentY() + " " + gameData.getCurrentY());
                    gameData.setPlayerNum(gameData.getPlayerNum() + 1);
                } else if (!gameData.getCurrentChosenPiece().move(gameData.getCurrentX(), gameData.getCurrentY(), gameData.getBoard().getBoard())) {
                    System.out.println("Poza możliwościami pionka lub jest tam inny pionek");
                    gameData.setCurrentChosenPiece(null);
                }
                gameData.getBoard().refreshBoard();
                gameData.getBoard().repaintColors();
                setCheck();
                isMate();
            }

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
        }


        public void mouseReleased(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }

    public void searchAndDestroy(Piece piece) {
        for (Player p : gameData.getPlayers()) {
            if (p != gameData.getCurrentTurn()) {
                for (ArrayList<Piece> pieces : p.playerPieces) {
                    for (Piece pieceN : pieces) {
                        if (pieceN.getX() == piece.getX() && pieceN.getY() == piece.getY()) {
                            pieces.remove(pieceN);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void useItem(Spot spot) {
        if (spot.item == 1) {
            Spot s;
            while (true) {
                Random random = new Random();
                s = gameData.getBoard().getBoard()[random.nextInt(Config.Instance().boardWidth - 1)][random.nextInt(Config.Instance().boardHeight - 1)];
                if (s.getPiece() != null) {
                    if (s.getPiece().getColor() != gameData.getCurrentTurn().getPlayerColor()) {
                        break;
                    }
                }
            }
            searchAndDestroy(s.getPiece());
            s.setPiece(null);
        } else if (spot.item == 2) {
            int newPiece = new Random().nextInt(5);
            Piece p;
            switch (newPiece) {
                case 0:
                    searchAndDestroy(gameData.getCurrentChosenPiece(), gameData.getCurrentTurn());
                    gameData.setCurrentChosenPiece(null);
                    p = new Pawn(gameData.getCurrentTurn().attackDirection);
                    p.setColor(gameData.getCurrentTurn().getPlayerColor());
                    gameData.setCurrentChosenPiece(p);
                    gameData.getCurrentChosenPiece().setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                    //configure it
                    break;
                case 1:
                    searchAndDestroy(gameData.getCurrentChosenPiece(), gameData.getCurrentTurn());
                    gameData.setCurrentChosenPiece(null);
                    p = new Rook();
                    p.setColor(gameData.getCurrentTurn().getPlayerColor());
                    gameData.setCurrentChosenPiece(p);
                    gameData.getCurrentChosenPiece().setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                    gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                    break;
                case 2:
                    searchAndDestroy(gameData.getCurrentChosenPiece(), gameData.getCurrentTurn());
                    gameData.setCurrentChosenPiece(null);
                    p = new Knight();
                    p.setColor(gameData.getCurrentTurn().getPlayerColor());
                    gameData.setCurrentChosenPiece(p);
                    gameData.getCurrentChosenPiece().setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                    gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                    //configure it
                    break;
                case 3:
                    searchAndDestroy(gameData.getCurrentChosenPiece(), gameData.getCurrentTurn());
                    gameData.setCurrentChosenPiece(null);
                    p = new Bishop();
                    p.setColor(gameData.getCurrentTurn().getPlayerColor());
                    gameData.setCurrentChosenPiece(p);
                    gameData.getCurrentChosenPiece().setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                    gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                    //configure it
                    break;
                case 4:
                    searchAndDestroy(gameData.getCurrentChosenPiece(), gameData.getCurrentTurn());
                    gameData.setCurrentChosenPiece(null);
                    p = new Queen();
                    p.setColor(gameData.getCurrentTurn().getPlayerColor());
                    gameData.setCurrentChosenPiece(p);
                    gameData.getCurrentChosenPiece().setLocation(gameData.getCurrentX(), gameData.getCurrentY());
                    gameData.getBoard().getBoard()[gameData.getCurrentX()][gameData.getCurrentY()].setPiece(gameData.getCurrentChosenPiece());
                    //configure it
                    break;
            }
            System.out.println("Zmieniłem sie w " + newPiece);
        }
        spot.item = 0;
    }

    public void searchAndDestroy(Piece piece, Player player) {

        for (ArrayList<Piece> pieces : player.playerPieces) {
            for (Piece pieceN : pieces) {
                if (pieceN.getX() == piece.getX() && pieceN.getY() == piece.getY()) {
                    pieces.remove(pieceN);
                    break;
                }
            }

        }
    }

    public void pawnAscension(Pawn pawn) {
        switch (pawn.getAttackDirection()) {
            case LEFT:
                if (pawn.getX() == Config.Instance().boardWidth - 1) {
                    for (ArrayList<Piece> p : gameData.getCurrentTurn().playerPieces) {
                        for (Piece piece : p) {
                            if (piece instanceof Pawn && piece == pawn) {
                                Queen queen = new Queen();
                                queen.setLocation(pawn.getX(), pawn.getY());
                                queen.setColor(pawn.getColor());
                                p.remove(piece);
                                p.add(queen);
                                gameData.setCurrentChosenPiece(queen);
                                break;
                            }
                        }
                    }
                }
                break;
            case RIGHT:
                if (pawn.getX() == 0) {
                    for (ArrayList<Piece> p : gameData.getCurrentTurn().playerPieces) {
                        for (Piece piece : p) {
                            if (piece instanceof Pawn && piece == pawn) {
                                Queen queen = new Queen();
                                queen.setX(pawn.getX());
                                queen.setY(pawn.getY());
                                queen.setColor(pawn.getColor());
                                p.remove(piece);
                                p.add(queen);
                                gameData.setCurrentChosenPiece(queen);
                                break;
                            }
                        }
                    }

                }
                break;
            case UP:
                if (pawn.getY() == 0) {
                    for (ArrayList<Piece> p : gameData.getCurrentTurn().playerPieces) {
                        for (Piece piece : p) {
                            if (piece instanceof Pawn && piece == pawn) {
                                Queen queen = new Queen();
                                queen.setX(pawn.getX());
                                queen.setY(pawn.getY());
                                queen.setColor(pawn.getColor());
                                p.remove(piece);
                                p.add(queen);
                                gameData.setCurrentChosenPiece(queen);
                                break;
                            }
                        }
                    }

                }
                break;
            case DOWN:
                if (pawn.getY() == Config.Instance().boardHeight - 1) {
                    for (ArrayList<Piece> p : gameData.getCurrentTurn().playerPieces) {
                        for (Piece piece : p) {
                            if (piece instanceof Pawn && piece == pawn) {
                                Queen queen = new Queen();
                                queen.setX(pawn.getX());
                                queen.setY(pawn.getY());
                                queen.setColor(pawn.getColor());
                                p.remove(piece);
                                p.add(queen);
                                gameData.setCurrentChosenPiece(queen);
                                break;
                            }
                        }
                    }

                }
                break;
        }
        gameData.getBoard().refreshBoard();

    }

    public synchronized boolean validateChoosenPiece() {
        boolean isCorrect = false;

        for (ArrayList<Piece> pieces : gameData.getCurrentTurn().playerPieces) {
            if (pieces.contains(gameData.getCurrentChosenPiece())) {
                isCorrect = true;
                break;
            }
        }
        return isCorrect;
    }

    //Ustawia szacha na królu pozostałych graczy
    public synchronized void setCheck() {
        for (ArrayList<Piece> pieces : gameData.getCurrentTurn().playerPieces) {
            for (Piece piece : pieces) {
                for (Spot s : piece.getPossibleMoves(gameData.getBoard().getBoard())) {
                    for (Player p : gameData.getPlayers()) {
                        if (p != gameData.getCurrentTurn()) {
                            for (ArrayList<Piece> pieces1 : p.playerPieces) {
                                for (Piece piece1 : pieces1) {
                                    if (piece1 instanceof King) {
                                        King king = (King) piece1;
                                        if (s.getY() == piece1.getY() && s.getX() == piece1.getX()) {
                                            System.out.println("SZACH");
                                            king.isChecked(true);
                                            gameData.getBoard().getBoard()[s.getX()][s.getY()].setColor(Color.yellow);
                                        } else {
                                            king.isChecked(false);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //Tablica ruchów królowi powodujące szach
    public synchronized Collection<Spot> mayBeChecked(King king) {
        List<Spot> impossibleMoves = new ArrayList<>();
        for (Player p : gameData.getPlayers()) {
            if (p != gameData.getCurrentTurn()) {
                for (ArrayList<Piece> pieces : p.playerPieces) {
                    for (Piece piece : pieces) {
                        for (Spot s : piece.getPossibleAttacks(gameData.getBoard().getBoard()))
                            if (king.getPossibleMoves(gameData.getBoard().getBoard()).contains(s)) {
                                impossibleMoves.add(s);
                            }
                    }
                }
            }
        }
        return impossibleMoves;
    }

    //Sprawdza czy jest szach na królu gracza
    public synchronized boolean isChecked() {
        boolean isChecked = false;
        for (ArrayList<Piece> pieces1 : gameData.getCurrentTurn().playerPieces) {
            for (Piece piece1 : pieces1) {
                if (piece1 instanceof King) {
                    King king = (King) piece1;
                    isChecked = king.getIsChecked();
                }
            }
        }
        return isChecked;
    }

    //Ustawia mat na królu gracza
    public synchronized void isMate() {
        List<Spot> impossibleMoves = new ArrayList<>();
        King king;
        for (ArrayList<Piece> aL : gameData.getCurrentTurn().playerPieces) {
            for (Piece playerPiece : aL) {
                if (playerPiece instanceof King) {
                    king = (King) playerPiece;
                    for (Player p : gameData.getPlayers()) {
                        if (p != gameData.getCurrentTurn()) {
                            for (ArrayList<Piece> pieces : p.playerPieces) {
                                for (Piece piece : pieces) {
                                    for (Spot s : piece.getPossibleAttacks(gameData.getBoard().getBoard()))
                                        if (king.getPossibleMoves(gameData.getBoard().getBoard()).contains(s)) {
                                            impossibleMoves.add(s);
                                        }
                                }
                            }
                        }
                    }
                    ArrayList<Boolean> mateTab = new ArrayList<>();
                    for (Spot kingSpot : king.getPossibleMoves(gameData.getBoard().getBoard())) {
                        if (impossibleMoves.contains(kingSpot)) {
                            mateTab.add(true);
                        }
                    }

                    if (mateTab.size() != 0 && mateTab.size() == king.getPossibleMoves(gameData.getBoard().getBoard(), mayBeChecked(king)).size()) {
                        king.setIsMate(true);
                        System.out.println("MAT!");
                        for (Player p : gameData.getPlayers()) {
                            if (p == gameData.getCurrentTurn()) {
                                gameData.getPlayers().remove(p);
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    //Sprawdza czy król aktualnego gracza ma mata
    public boolean checkForMate() {
        boolean playerMate = false;

        for (ArrayList<Piece> pieces1 : gameData.getCurrentTurn().playerPieces) {
            for (Piece piece1 : pieces1) {
                if (piece1 instanceof King) {
                    King king = (King) piece1;
                    playerMate = king.getIsMate();
                }
            }
        }
        return playerMate;
    }

}
