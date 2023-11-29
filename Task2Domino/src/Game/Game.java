package Game;

import Player.Bot;

public class Game {



    public static void main(String[] args) {
        Bot bot1 = new Bot("IgRok1");
        Bot bot2 = new Bot("Igrok2");
        GameState gs = new GameState(bot1, bot2);
        gs.runGame();
    }
}
