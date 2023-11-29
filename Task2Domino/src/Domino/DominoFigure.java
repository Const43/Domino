package Domino;

public class DominoFigure {
    protected final DominoVertex firstVertex;
    protected final DominoVertex secondVertex;

    public DominoFigure(int valueFirstVertex, int valueSecondVertex) {
        this.firstVertex = new DominoVertex(valueFirstVertex, this);
        this.secondVertex = new DominoVertex(valueSecondVertex, this);
    }

    public DominoVertex getFirstVertex() {
        return firstVertex;
    }

    public DominoVertex getSecondVertex() {
        return secondVertex;
    }


    @Override
    public String toString() {
        return "Фигура домино со значениями " +
                  firstVertex.getValue() +
                " : " + secondVertex.getValue();
    }
}
