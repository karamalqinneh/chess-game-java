package com.chess.runner;

import com.chess.piece.AbstractPiece;

import java.util.ArrayList;
import java.util.HashMap;

public class DeadPieces {

    private static HashMap<String, ArrayList<AbstractPiece>> lightPieces = new HashMap<>();
    private static HashMap <String, ArrayList<AbstractPiece>> darkPieces = new HashMap<>();


    public static HashMap getLightPieces() {
        return lightPieces;
    }

    public static HashMap getDarkPieces() {
        return darkPieces;
    }

    public static void setLightPieces(AbstractPiece piece) {
        ArrayList<AbstractPiece> current = new ArrayList<>();
        if (lightPieces.containsKey(piece.getName())) {
            current.addAll(lightPieces.get(piece.getName()));
        }
        current.add(piece);
        lightPieces.put(piece.getName(), current);
    }

    public static void setDarkPieces(AbstractPiece piece) {
        ArrayList<AbstractPiece> current;
        if (darkPieces.containsKey(piece.getName())) {
            current = new ArrayList<>(darkPieces.get(piece.getName()));
        } else {
            current = new ArrayList<>();
        }
        current.add(piece);
        darkPieces.put(piece.getName(), current);
    }
}
