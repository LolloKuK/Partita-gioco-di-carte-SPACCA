package game;

import java.io.ObjectInputStream;
import java.util.ArrayList;

public class TableForFour extends Table implements Game {

    public TableForFour(Player g1, Player g2, Player g3, Player g4) {
        this.cardOnTable = new ArrayList<>();
        this.playerInGame = new Player[]{g1, g2, g3, g4};
        this.playerOrder = new ArrayList<>();
        playerOrder.add(g1);
        playerOrder.add(g2);
        playerOrder.add(g3);
        playerOrder.add(g4);
        setEffectList();
    }

    @Override
    public void resetScores() {
        getFirst().resetPoints();
        getSecond().resetPoints();
        getThird().resetPoints();
        getFourth().resetPoints();
    }

    @Override
    public void fillHand() {
        getFirst().getHand().refillHand(getFirst().getDeck());
        getSecond().getHand().refillHand(getSecond().getDeck());
        getThird().getHand().refillHand(getThird().getDeck());
        getFourth().getHand().refillHand(getFourth().getDeck());
    }

    public void whoWin() {
        if (cardOnTable.get(1).getValue() > cardOnTable.getFirst().getValue() + getEffect() &&
                cardOnTable.get(1).getValue() >= cardOnTable.get(2).getValue() &&
                cardOnTable.get(1).getValue() >= cardOnTable.get(3).getValue()) {

            Player l = playerOrder.getFirst();
            playerOrder.set(0, playerOrder.get(1));
            playerOrder.set(1, playerOrder.get(2));
            playerOrder.set(2, playerOrder.get(3));
            playerOrder.set(3, l);
        }
        else if (cardOnTable.get(2).getValue() > cardOnTable.getFirst().getValue() + getEffect() &&
                cardOnTable.get(2).getValue() > cardOnTable.get(1).getValue() &&
                cardOnTable.get(2).getValue() >= cardOnTable.get(3).getValue()) {

            Player w = playerOrder.get(2);
            Player l = playerOrder.get(3);
            playerOrder.set(2, playerOrder.get(0));
            playerOrder.set(3, playerOrder.get(1));
            playerOrder.set(0, w);
            playerOrder.set(1, l);
        }
        else if (cardOnTable.get(3).getValue() > cardOnTable.getFirst().getValue() + getEffect() &&
                cardOnTable.get(3).getValue() > cardOnTable.get(1).getValue() &&
                cardOnTable.get(3).getValue() > cardOnTable.get(2).getValue()) {

            Player w = playerOrder.get(3);
            playerOrder.set(3, playerOrder.get(2));
            playerOrder.set(2, playerOrder.get(1));
            playerOrder.set(1, playerOrder.get(0));
            playerOrder.set(0, w);
        }
    }
    @Override
    public void getTurnWinner() {
        printTable();
        if (cardOnTable.getFirst().isSpecial()) {
            if (cardOnTable.getFirst().getName().equals("Malus")) {
                playerOrder.getFirst().addPoints(roundTotalScore() / 2);
            }
            else if (cardOnTable.getFirst().getName().equals("Bonus")) {
                whoWin();
                playerOrder.getFirst().addPoints(0);
            }
        }
        else if (cardOnTable.get(1).isSpecial()) {
            if (cardOnTable.get(1).getName().equals("Malus")) {

                playerOrder.get(1).addPoints(roundTotalScore() / 2);
                Player l = playerOrder.getFirst();
                playerOrder.set(0, playerOrder.get(1));
                playerOrder.set(1, playerOrder.get(2));
                playerOrder.set(2, playerOrder.get(3));
                playerOrder.set(3, l);
            }
            else if (cardOnTable.get(1).getName().equals("Bonus")) {
                whoWin();
                playerOrder.getFirst().addPoints(0);
            }
        }
        else if (cardOnTable.get(2).isSpecial()) {

            if (cardOnTable.get(2).getName().equals("Malus")) {

                playerOrder.get(2).addPoints(roundTotalScore() / 2);
                Player w = playerOrder.get(2);
                Player l = playerOrder.get(3);
                playerOrder.set(2, playerOrder.get(0));
                playerOrder.set(3, playerOrder.get(1));
                playerOrder.set(0, w);
                playerOrder.set(1, l);
            }
            else if (cardOnTable.get(2).getName().equals("Bonus")) {
                whoWin();
                playerOrder.getFirst().addPoints(0);
            }
        }
        else if (cardOnTable.get(3).isSpecial()) {

            if (cardOnTable.get(3).getName().equals("Malus")) {

                playerOrder.get(3).addPoints(roundTotalScore() / 2);
                Player w = playerOrder.get(3);
                playerOrder.set(3, playerOrder.get(2));
                playerOrder.set(2, playerOrder.get(1));
                playerOrder.set(1, playerOrder.get(0));
                playerOrder.set(0, w);
            }
            else if (cardOnTable.get(3).getName().equals("Bonus")) {
                whoWin();
                playerOrder.getFirst().addPoints(0);
            }
        }

        else if (cardOnTable.getFirst().getValue() + getEffect() >= cardOnTable.get(1).getValue() &&
                cardOnTable.getFirst().getValue() + getEffect() >= cardOnTable.get(2).getValue() &&
                cardOnTable.getFirst().getValue() >= cardOnTable.get(3).getValue()) {

            playerOrder.getFirst().addPoints(roundTotalScore());
        }
        else {
            whoWin();
            playerOrder.getFirst().addPoints(roundTotalScore());
        }

        System.out.println(getFirst().getName()+ "\t" + getFirst().getPoints());
        cardOnTable.clear();
        setEffect();
    }

    @Override
    public void getRoundWinner() {
        if (getFirst().getPoints() > getSecond().getPoints() &&
                getFirst().getPoints() > getThird().getPoints() &&
                getFirst().getPoints() > getFourth().getPoints()) {

            getFirst().addTurnScore();
            System.out.println("Vince il round: " + getFirst().getName() + "\t" + getFirst().getTurnScore());
        }
        else if (getSecond().getPoints() > getFirst().getPoints() &&
                getSecond().getPoints() > getThird().getPoints() &&
                getSecond().getPoints() > getFourth().getPoints()) {

            getSecond().addTurnScore();
            System.out.println("Vince il round: " + getSecond().getName() + "\t" + getSecond().getTurnScore());
        }
        else if (getThird().getPoints() > getFirst().getPoints() &&
                getThird().getPoints() > getSecond().getPoints() &&
                getThird().getPoints() > getFourth().getPoints()) {

            getThird().addTurnScore();
            System.out.println("Vince il round: " + getThird().getName() + "\t" + getThird().getTurnScore());
        }
        else if (getFourth().getPoints() > getFirst().getPoints() &&
                getFourth().getPoints() > getSecond().getPoints() &&
                getFourth().getPoints() > getThird().getPoints()) {

            getFourth().addTurnScore();
            System.out.println("Vince il round: " + getFourth().getName() + "\t" + getFourth().getTurnScore());
        }
        else {
            System.out.println("Pareggio!");
        }
        resetScores();
    }

    @Override
    public Player getGameWinner() {

        Player winner = null;
        if (getFirst().getTurnScore() > getSecond().getTurnScore() &&
                getFirst().getTurnScore() > getThird().getTurnScore() &&
                getFirst().getTurnScore() > getFourth().getTurnScore()) {

            winner = getFirst();
        }
        else if (getSecond().getTurnScore() > getFirst().getTurnScore() &&
                getSecond().getTurnScore() > getThird().getTurnScore() &&
                getSecond().getTurnScore() > getFourth().getTurnScore()) {

            winner = getSecond();
        }
        else if(getThird().getTurnScore() > getFirst().getTurnScore() &&
                getThird().getTurnScore() > getSecond().getTurnScore() &&
                getThird().getTurnScore() > getFourth().getTurnScore()) {

            winner = getThird();
        }
        else {
            winner = getFourth();
        }
        return winner;
    }

    @Override
    public void matchStart() {

        do {
            fillHand();
            for (int i = 0; i < 5; i++) {
                addOnTable(getFirst(), 1);
                addOnTable(getSecond(), 1);
                addOnTable(getThird(), 1);
                addOnTable(getFourth(), 1);
                getTurnWinner();
                cardOnTable.clear();
            }
            getRoundWinner();
            System.out.println();
        } while (getGameWinner().getTurnScore() < 2);
        System.out.println();
        System.out.println("Fine partita. Vince: " + getGameWinner().getName() + "\t" + getGameWinner().getTurnScore());
    }
}
