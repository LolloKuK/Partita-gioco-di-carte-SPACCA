package game;

import java.util.ArrayList;

public class Hand {

    public int handCard;
    public ArrayList<Card> mano = new ArrayList<>();

    public Hand() {
        this.handCard = 0;
    }

    public int size() { return mano.size(); }

    public void refillHand(Deck deck) {
        for(int i = 0; i < 5; i++) {
            mano.add(deck.getFirstCard());
            handCard++;
        }
    }

    /*
    public void printHand() {
        for (Card card : mano) {
            System.out.println(card.getName() + " " + card.getValue());
        }
    }
    */

    public Card getOneCard(int i) {
        Card cartaGiocata = mano.get(i-1);
        mano.remove(i-1);
        return cartaGiocata;
    }
}
