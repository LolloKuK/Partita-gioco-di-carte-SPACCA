package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Table {

    public Player[] playerInGame;
    public ArrayList<Card> cardOnTable;
    public ArrayList<Player> playerOrder;
    public ArrayList<Integer> effectList;

    public Table(Player g1, Player g2) {
        this.cardOnTable = new ArrayList<>();
        this.playerInGame = new Player[]{g1, g2};
        this.playerOrder = new ArrayList<>();
        playerOrder.add(g1);
        playerOrder.add(g2);
        this.effectList = new ArrayList<>();
        setEffectList();
    }

    /*
    public Table(Player g1, Player g2, Player g3) {
        this.cardOnTable = new ArrayList<>();
        this.playerInGame = new Player[]{g1, g2, g3};
        this.playerOrder = new ArrayList<>();
        playerOrder.add(g1);
        playerOrder.add(g2);
        playerOrder.add(g3);
    }
    public Table(Player g1, Player g2, Player g3, Player g4) {
        this.cardOnTable = new ArrayList<>();
        this.playerInGame = new Player[]{g1, g2, g3, g4};
        this.playerOrder = new ArrayList<>();
        playerOrder.add(g1);
        playerOrder.add(g2);
        playerOrder.add(g3);
        playerOrder.add(g4);
    }
     */

    public Player getFirst() { return playerOrder.getFirst(); }
    public Player getLast() { return playerOrder.getLast(); }

    /*
    public void printTable() {
        System.out.println(getEffect());
        for(int i = 0; i < cardOnTable.size(); i++) {
            System.out.println(cardOnTable.get(i).getName() + "\t" + cardOnTable.get(i).getValue() + "\t\t" + playerOrder.get(i).getName());
        }
        System.out.println(getTurnWinner().getName()+ "\t" + playerOrder.getFirst().getPoints());
    }
    */

    public void addOnTable(Player p, int i) {
        cardOnTable.add(p.playCard(i));
    }

    public int roundTotalScore() {
        int roundScore = 0;
        for (Card card : cardOnTable) {
            roundScore += card.getValue();
        }
        return roundScore;
    }

    public void resetScores() {
        getFirst().resetPoints();
        getLast().resetPoints();
    }

    public void setEffectList(){
        String pathFile = "/Users/lorenzocuoco/Desktop/Cartelle/Programmazione/IntelliJ-workspace/Partita/src/saved_data/effect.txt";
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

    public void fillHand() {
        getFirst().getDeck().shuffleDeck();
        getLast().getDeck().shuffleDeck();
        getFirst().getHand().refillHand(getFirst().getDeck());
        getLast().getHand().refillHand(getLast().getDeck());
    }
    public void  getTurnWinner() {
        if (cardOnTable.getFirst().isSpecial()) {
            if (cardOnTable.getFirst().getName().equals("Malus")) {
                playerOrder.getFirst().addPoints((roundTotalScore() + getEffect()) / 2);
            }
            else if (cardOnTable.getFirst().getName().equals("Bonus")) {
                playerOrder.getLast().addPoints(0);
                Player loser = playerOrder.getFirst();
                playerOrder.set(0, playerOrder.getLast());
                playerOrder.set(1, loser);
            }
        }
        else if (cardOnTable.getLast().isSpecial()) {
            if (cardOnTable.getLast().getName().equals("Malus")) {
                playerOrder.getLast().addPoints((roundTotalScore() + getEffect()) / 2);
                Player loser = playerOrder.getFirst();
                playerOrder.set(0, playerOrder.getLast());
                playerOrder.set(1, loser);
            }
            else if (cardOnTable.getLast().getName().equals("Bonus")) {
                playerOrder.getFirst().addPoints(0);
            }
        }
        else if (cardOnTable.getFirst().getValue() + getEffect() >= cardOnTable.getLast().getValue()) {
            playerOrder.getFirst().addPoints(roundTotalScore() + getEffect());
        }
        else {
            playerOrder.getLast().addPoints(roundTotalScore() + getEffect());
            Player loser = playerOrder.getFirst();
            playerOrder.set(0, playerOrder.getLast());
            playerOrder.set(1, loser);
        }
        cardOnTable.clear();
        setEffect();
        //return playerOrder.getFirst();
    }
}