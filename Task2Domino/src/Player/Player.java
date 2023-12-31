package Player;

import Domino.DominoFigure;
import Game.GameState;
import Game.GameStateInterface;

import java.util.List;
import java.util.Random;

abstract class Player implements PlayerInterface {
    protected List<DominoFigure> figures;
    protected static Random RANDOM = new Random();
    protected int points;
    protected String name;

    public int getPoints() {
        return points;
    }

    public void setFigures(List<DominoFigure> figures) {
        this.figures = figures;
    }

    public void setPoints(int points) {
        this.points = points;
    }


}
