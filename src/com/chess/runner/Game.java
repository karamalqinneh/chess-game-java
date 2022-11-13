package com.chess.runner;

import java.util.*;

import com.chess.board.Board;
import com.chess.common.File;
import com.chess.common.Location;
import com.chess.piece.AbstractPiece;
import com.chess.piece.King;
import com.chess.piece.Movable;
import com.chess.piece.PieceColor;
import com.chess.squares.Square;

public class Game {
    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        Scanner scanner = new Scanner(System.in);
        AbstractPiece lightKing = board.getLightPieces().get(4);
        AbstractPiece darkKing = board.getDarkPieces().get(4);
        // true -> game is not finished.
        while(true) {
            int gameStateHandler = gameState();
            if(gameStateHandler == 1) {
                System.out.println("TIE");
                break;
            } else if (gameStateHandler == 2) {
                System.out.println("Dark Won");
                break;
            } else if (gameStateHandler == 3) {
                System.out.println("Light Won");
                break;
            }
            String line = scanner.nextLine();
            if(Objects.equals(line, "quit")) break;
            try {
                String[] fromTo = line.split("->");
                File fromFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[0].charAt(0))));
                int fromRank = Integer.parseInt(String.valueOf(fromTo[0].charAt(1)));
                File toFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[1].charAt(0))));
                int toRank = Integer.parseInt(String.valueOf(fromTo[1].charAt(1)));

                Square fromSq = board.getLocationSquareMap().get(new Location(fromFile, fromRank));
                Location desiredMove = new Location(toFile, toRank);
                if(fromSq.getCurrentPiece().getValidMoves(board).contains(desiredMove)) {
                    Square toSq = board.getLocationSquareMap().get(new Location(toFile, toRank));

                    fromSq.getCurrentPiece().makeMove(toSq);
                    fromSq.reset();

                    board.printBoard();
                } else {
                    System.out.println("********* INVALID MOVE, TRY AGAIN *********");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("********* INVALID MOVE, TRY AGAIN *********");
                continue;
            }
        }

    }

    public static int gameState() {
        HashMap lightPieces = DeadPieces.getLightPieces();
        HashMap darkPieces = DeadPieces.getDarkPieces();
        int lightPiecesCount = currentPieceCount(lightPieces);
        int darkPiecesCount = currentPieceCount(darkPieces);
        if (lightPiecesCount == 15 && darkPiecesCount == 15) {
            return 1;
        } else if (lightPieces.containsKey("King")) {
            return 2;
        } else if (darkPieces.containsKey("King")){
            return 3;
        }
        return 0;
    }

    public static int currentPieceCount(HashMap map) {
        Iterator<HashMap.Entry<String, ArrayList<AbstractPiece>>> itr = map.entrySet().iterator();
        int pieceCount = 0;
        while(itr.hasNext()) {
            HashMap.Entry<String, ArrayList<AbstractPiece>> entry = itr.next();
            pieceCount += entry.getValue().size();
        }
        return pieceCount;
    }
}
