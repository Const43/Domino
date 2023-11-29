package Player;

import Domino.DominoFigure;
import Game.GameState;

import java.util.List;

public interface PlayerInterface {

    public DominoFigure addFigureFromPlayersFigure(GameState gameState);
    public void getFigures(DominoFigure figure);
    public void pass();
    public String getName();
    public int getCountFigures();
    public List<DominoFigure> getFiguresDanger();
}
