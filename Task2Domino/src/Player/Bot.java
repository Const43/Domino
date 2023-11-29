package Player;

import Domino.DominoFigure;
import Domino.DominoVertex;
import Game.GameState;
import Player.Player;

import java.util.ArrayList;
import java.util.List;

public class Bot extends Player {
    public Bot(String name) {
        figures = new ArrayList<>();
        this.name = name;
    }

    @Override
    public DominoFigure addFigureFromPlayersFigure(GameState gameState) {
        if(gameState.getFiguresOnBoard().size() == 0){
            return figures.remove(RANDOM.nextInt(figures.size()));
        }
        List<DominoVertex> activeVertex = gameState.getActiveVertex();
        for (int i = 0; i < figures.size(); i++) {
            for(DominoVertex vertex : activeVertex){
                if(vertex.getValue() == figures.get(i).getFirstVertex().getValue() ||
                vertex.getValue() == figures.get(i).getSecondVertex().getValue()){
                    System.out.println("Игрок " + name + " добавляет фигурку: " + figures.get(i) );
                    return figures.remove(i);
                };
            }
        }
        return null;
    }

    @Override
    public void getFigures(DominoFigure figure) {
        if(figures.size() > 8){
            return;
        }
        figures.add(figure);
        System.out.println("Игрок " + name + " получил фигуру: " +  figure);
    }

    @Override
    public void pass() {

    }

    public List<DominoFigure> getFiguresDanger(){
        return figures;
    }
    @Override
    public String getName() {
        return name;
    }

    public int getCountFigures(){
        return figures.size();
    }

}
