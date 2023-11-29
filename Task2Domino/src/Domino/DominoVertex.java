package Domino;

public class DominoVertex
{
    private final int value;
    private DominoVertex connectVertex;
    private final DominoFigure figure;

    public int getValue() {
        return value;
    }

    public DominoVertex(int value, DominoFigure figure) {
        this.figure = figure;
        this.value = value;
        if(isValidValue(value)){
            throw new RuntimeException("Неправильное значение вершины");
        }
    }
    private boolean isValidValue(int value){
        return value < 0 || value > 6;
    }

    public void setConnectVertex(DominoVertex connectVertex) {
        if(this.connectVertex != null){
            return;
        }
        if(connectVertex.getValue() != value){
            return;
        }
        this.connectVertex = connectVertex;
        System.out.println(this.figure + " соединена с " + connectVertex.figure);
    }

    public DominoVertex getConnectVertex() {
        return connectVertex;
    }

    @Override
    public String toString() {
        return "" +
                 value;

    }
}
