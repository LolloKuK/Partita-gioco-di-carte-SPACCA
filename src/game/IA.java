package game;

import java.util.Random;

public class IA extends Player {

    Random random = new Random();

    public IA(String nome, Deck deck) {
        super(nome, deck);
    }

    public Card playCard(int i) {
        //i = random.nextInt(mano.size() -1);
        return getHand().getOneCard(i);
    }
}
