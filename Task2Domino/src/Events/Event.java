package Events;

import Domino.DominoFigure;
import Domino.DominoVertex;
import Player.PlayerInterface;

public class Event {
    PlayerInterface player;

    DominoFigure dominoFigure;
    DominoVertex dominoVertex;

    public Event( DominoFigure dominoFigure, DominoVertex dominoVertex, PlayerInterface player) {
        this.dominoFigure = dominoFigure;
        this.dominoVertex = dominoVertex;
        this.player = player;
    }

    public DominoFigure getDominoFigure() {
        return dominoFigure;
    }

    public DominoVertex getDominoVertex() {
        return dominoVertex;
    }

    public PlayerInterface getPlayer() {
        return player;
    }
}
