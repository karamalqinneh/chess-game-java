package com.chess.piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.runner.DeadPieces;
import com.chess.squares.Square;

public class Queen extends AbstractPiece implements Movable {

    private Movable bishop;
    private Movable rook;


    public Queen(PieceColor pieceColor) {
        super(pieceColor);
        this.name = "Queen";
        this.bishop = new Bishop(pieceColor);
        this.rook = new Rook(pieceColor);
    }

    public Queen(PieceColor pieceColor, Movable bishop, Movable rook) {
        super(pieceColor);
        this.name = "Queen";
        this.bishop = bishop;
        this.rook = rook;
    }

    @Override
    public List<Location> getValidMoves(Board board) {
        List<Location> moveCandidates = new ArrayList<>();
        moveCandidates.addAll(bishop.getValidMoves(board, this.getCurrentSquare()));
        moveCandidates.addAll(rook.getValidMoves(board, this.getCurrentSquare()));
        return moveCandidates;
    }

    @Override
    public List<Location> getValidMoves(Board board, Square square) {
        return null;
    }

    @Override
    public void makeMove(Square square) {
        if (square.isOccupied()) {
            if (square.getCurrentPiece().pieceColor.toString().equals("LIGHT")) {
                DeadPieces.setLightPieces(square.getCurrentPiece());
            } else {
                DeadPieces.setDarkPieces(square.getCurrentPiece());
            }
        }
        this.currentSquare.setOccupied(false);
        this.setCurrentSquare(square);
        square.setCurrentPiece(this);
        square.setOccupied(true);
    }
}