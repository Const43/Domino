package Game;

import Domino.DominoFigure;
import Domino.DominoVertex;

import java.util.List;

public interface GameStateInterface {
    public List<DominoFigure> getFiguresOnBoard();
    public List<DominoVertex> getActiveVertex();

    public void addFigureOnBoard(DominoFigure figure);
    public void previousStep();

}
