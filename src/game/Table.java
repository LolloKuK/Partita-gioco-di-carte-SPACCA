package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public abstract class Table {

    public Player[] playerInGame;
    public ArrayList<Card> cardOnTable;
    public ArrayList<Player> playerOrder;
    public ArrayList<Integer> effectList = new ArrayList<>();

    public Table() { }

    public Player getFirst() { return playerOrder.getFirst(); }
    public Player getSecond() { return playerOrder.get(1); }
    public Player getThird() { return playerOrder.get(2); }
    public Player getFourth() { return playerOrder.get(3); }

    public void printTable() {
        System.out.println(getEffect());
        for(int i = 0; i < cardOnTable.size(); i++) {
            System.out.println(cardOnTable.get(i).getName() + "\t" + cardOnTable.get(i).getValue() + "\t\t" + playerOrder.get(i).getName());
        }
    }

    public void addOnTable(Player p, int i) {
        cardOnTable.add(p.playCard(i));
    }

    public int roundTotalScore() {
        int roundScore = 0;
        for (Card card : cardOnTable) {
            roundScore += card.getValue();
        }
        roundScore += getEffect();
        return roundScore;
    }

    public void setEffectList(){
        String pathFile = "/Users/lorenzocuoco/Desktop/Cartelle/IntelliJ-workspace/Partita/src/saved_data/effect.txt";
        //da cambiare nel caso si usi su un'altro pc
        System.out.println(pathFile);
        try (BufferedReader reader = new BufferedReader(new FileReader(pathFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                int n = Integer.parseInt(linea.trim());
                effectList.add(n);
            }
        }
        catch (IOException e) { System.out.println("File non trovato"); }
    }
    public void setEffect() {
        Random random = new Random();
        int i = effectList.size()-1;
        int e = random.nextInt( i + 1);
        playerOrder.getFirst().addEffect(effectList.get(e));
    }
    public int getEffect() { return playerOrder.getFirst().getEffect(); }

    public abstract void resetScores();
    public abstract void fillHand();
    public abstract void whoWin();
    public abstract void getTurnWinner();
    public abstract void getRoundWinner();
    public abstract Player getGameWinner();
}
