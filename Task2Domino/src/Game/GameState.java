package Game;

import Domino.DominoFigure;
import Domino.DominoVertex;
import Player.PlayerInterface;
import Events.Event;

import java.util.*;

public class GameState implements GameStateInterface{

    final static int MAX_VALUE = 6;

    List<DominoFigure> figures = new ArrayList<>();
    Stack<Event>  stackOfEvents = new Stack<>();
    PlayerInterface move;
    PlayerInterface player1;
    PlayerInterface player2;
    private List<DominoVertex> activeVertex = new ArrayList<>();
    private List<DominoFigure> figuresOnBoard = new ArrayList<>();

    public GameState(PlayerInterface player1, PlayerInterface player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    private void createFigures(){
        for(int i = 0; i <= MAX_VALUE; i++){
            for(int j = 0; j <= MAX_VALUE; j++){
                if(i == j){
                    continue;
                }
                if(j < i){
                    continue;
                }
                figures.add(new DominoFigure(i, j));
            }
        }
        Collections.shuffle(figures);
        System.out.println("Набор фигур создан");
    }

    public List<DominoVertex> getActiveVertex() {
        return new ArrayList<>(activeVertex);
    }

    @Override
    public void addFigureOnBoard(DominoFigure figure) {
        if(figuresOnBoard.size() == 0){
            figuresOnBoard.add(figure);
            activeVertex.add(figure.getFirstVertex());
            activeVertex.add(figure.getSecondVertex());
            System.out.println(move.getName() + " положил фигурку " + figure);
            return;
        }

        DominoVertex vertex1 = null;
        DominoVertex vertex2 = null;
        for(DominoVertex vertex: activeVertex){
            if(vertex.getValue() == figure.getFirstVertex().getValue()){
                vertex1 = vertex;
            }

            if(vertex.getValue() == figure.getSecondVertex().getValue()){
                vertex2 = vertex;
            }
        }
        if(vertex1 != null){
            figuresOnBoard.add(figure);
            vertex1.setConnectVertex(figure.getFirstVertex());
            figure.getFirstVertex().setConnectVertex(vertex1);
            activeVertex.add(figure.getSecondVertex());
            activeVertex.remove(vertex1);
            stackOfEvents.push(new Event(figure, figure.getFirstVertex(), null));
        }

        if(vertex2 != null){
            figuresOnBoard.add(figure);
            vertex2.setConnectVertex(figure.getFirstVertex());
            figure.getSecondVertex().setConnectVertex(vertex2);
            activeVertex.add(figure.getFirstVertex());
            activeVertex.remove(vertex2);
            stackOfEvents.push(new Event(figure, figure.getSecondVertex(), null));
        }
    }

    @Override
    public void previousStep() {

        if(stackOfEvents.empty()){
            System.out.println("Список событий пуст");
            return;
        }
        Event event = stackOfEvents.pop();



        if(event.getPlayer() != null){
            event.getPlayer().getFiguresDanger().remove(event.getDominoFigure());
            return;
        }
        figuresOnBoard.remove(event.getDominoFigure());
        activeVertex.remove(event.getDominoFigure().getFirstVertex());
        activeVertex.remove(event.getDominoFigure().getSecondVertex());
        activeVertex.add(event.getDominoVertex().getConnectVertex());
        System.out.println("Поле было откачено на шаг назал");
    }

    public void runGame(){
        createFigures();
        for(int i = 0; i < MAX_VALUE + 1; i++){
            player1.getFigures(figures.remove(figures.size() - 1));
            player2.getFigures(figures.remove(figures.size() - 1));
        }
        System.out.println("-----------------------------");
        Scanner s = new Scanner(System.in);
        move = player1;
        int counter = 0;
        boolean flagPlay = true;
        while( flagPlay){
            System.out.println("Для продолжения нажмите 'N', \"Откатиться назад 'P', \"Информация 'I'");
            String string = s.nextLine();
            if(Objects.equals(string, "P")){
                previousStep();
                continue;
            }
            if(Objects.equals(string, "I")){
                System.out.println("Свободные вершины: " + activeVertex);
                System.out.println("Фигуры на столе: " + figuresOnBoard);
                System.out.println("Количесвто фигур у первого игрока: " + player1.getCountFigures());
                System.out.println("Количесвто фигур у второго игрока: " + player2.getCountFigures());
                continue;
            }
            DominoFigure figure = move.addFigureFromPlayersFigure(this);
            if(figure != null){
                addFigureOnBoard(figure);
                if(move == player1){
                    move = player2;
                }else{
                    move = player1;
                }
                continue;
            }

            if(figures.size() != 0){
                DominoFigure tmpFigure = figures.remove(figures.size() - 1);
                move.getFigures(tmpFigure);
                stackOfEvents.push(new Event(tmpFigure, null,move));

            }else {
                System.out.println("Фигуры закончились");


                counter++;

                if(move == player1){
                    move = player2;
                }else{
                    move = player1;
                }
            }
            if(player1.getCountFigures() == 0){
                System.out.println( player1.getName() + " победил у него закончились фигурки...");
                flagPlay = false;
            }
            if(player2.getCountFigures() == 0){
                System.out.println( player2.getName() + " победил у него закончились фигурки...");
                flagPlay = false;
            }
            if(counter == 2){
                if(player1.getCountFigures()  < player2.getCountFigures()){
                    System.out.println( player1.getName() + " победил у него меньше фигурок");
                }else{
                    System.out.println( player2.getName() + " победил у него меньше фигурок");

                }
                return;
            }
        }
    }


    public List<DominoFigure> getFiguresOnBoard() {
        return new ArrayList<>(figuresOnBoard);
    }
}
