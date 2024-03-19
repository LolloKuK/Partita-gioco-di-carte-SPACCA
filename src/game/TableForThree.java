package game;

import java.util.ArrayList;

public class TableForThree extends Table implements Game{

    public TableForThree(Player g1, Player g2, Player g3) {
        this.cardOnTable = new ArrayList<>();
        this.playerInGame = new Player[]{g1, g2, g3};
        this.playerOrder = new ArrayList<>();
        playerOrder.add(g1);
        playerOrder.add(g2);
        playerOrder.add(g3);
        setEffectList();
    }

    @Override
    public void resetScores() {
        getFirst().resetPoints();
        getSecond().resetPoints();
        getThird().resetPoints();
    }

    @Override
    public void fillHand() {
        getFirst().getHand().refillHand(getFirst().getDeck());
        getSecond().getHand().refillHand(getSecond().getDeck());
        getThird().getHand().refillHand(getThird().getDeck());
    }

    public void whoWin() {
        if (cardOnTable.get(1).getValue() > (cardOnTable.getFirst().getValue() + getEffect()) &&
                cardOnTable.get(1).getValue() >= cardOnTable.get(2).getValue()) {

            Player l = playerOrder.getFirst();
            playerOrder.set(0, playerOrder.get(1));
            playerOrder.set(1, playerOrder.get(2));
            playerOrder.set(2, l);
        }
        else if (cardOnTable.get(2).getValue() > (cardOnTable.getFirst().getValue() + getEffect()) &&
                cardOnTable.get(2).getValue() > cardOnTable.get(1).getValue()) {

            Player w = playerOrder.get(2);
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

                playerOrder.getFirst().addPoints((roundTotalScore()) / 2);
            }
            else if (cardOnTable.getFirst().getName().equals("Bonus")) {

                whoWin();
                playerOrder.getFirst().addPoints(0);
            }
        }
        else if (cardOnTable.get(1).isSpecial()) {

            if (cardOnTable.get(1).getName().equals("Malus")) {

                playerOrder.get(1).addPoints(roundTotalScore() / 2);
                Player loser = playerOrder.getFirst();
                playerOrder.set(0, playerOrder.get(1));
                playerOrder.set(1, playerOrder.get(2));
                playerOrder.set(2, loser);
            }
            else if (cardOnTable.get(1).getName().equals("Bonus")) {

                whoWin();
                playerOrder.getFirst().addPoints(0);
            }
        }
        else if (cardOnTable.get(2).isSpecial()) {

            if (cardOnTable.get(2).getName().equals("Malus")) {

                playerOrder.get(2).addPoints(roundTotalScore() / 2);
                Player loser = playerOrder.getFirst();
                playerOrder.set(0, playerOrder.get(2));
                playerOrder.set(2, playerOrder.get(1));
                playerOrder.set(1, loser);
            }
            else if (cardOnTable.get(2).getName().equals("Bonus")) {

                playerOrder.getFirst().addPoints(0);
            }
        }

        else if (cardOnTable.getFirst().getValue() + getEffect() >= cardOnTable.get(1).getValue() &&
                cardOnTable.getFirst().getValue() + getEffect() >= cardOnTable.get(2).getValue()) {

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
                getFirst().getPoints() > getThird().getPoints()) {

            getFirst().addTurnScore();
            System.out.println("Vince il round: " + getFirst().getName() + "\t" + getFirst().getTurnScore());
        }
        else if (getSecond().getPoints() > getFirst().getPoints() &&
                getSecond().getPoints() > getThird().getPoints()) {

            getSecond().addTurnScore();
            System.out.println("Vince il round: " + getSecond().getName() + "\t" + getSecond().getTurnScore());
        }
        else if (getThird().getPoints() > getFirst().getPoints() &&
                getThird().getPoints() > getSecond().getPoints()) {

            getThird().addTurnScore();
            System.out.println("Vince il round: " + getThird().getName() + "\t" + getThird().getTurnScore());
        }
        else {
            System.out.println("Pareggio!");
        }
        resetScores();
    }

    @Override
    public Player getGameWinner() {

        Player winner;
        if (getFirst().getTurnScore() > getSecond().getTurnScore() &&
                getFirst().getTurnScore() > getThird().getTurnScore()) {

            winner = getFirst();
        }
        else if (getSecond().getTurnScore() > getFirst().getTurnScore() &&
                getSecond().getTurnScore() > getThird().getTurnScore()) {

            winner = getSecond();
        }
        else {
            winner = getThird();
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
                getTurnWinner();
            }
            getRoundWinner();
            System.out.println();
        } while (getGameWinner().getTurnScore() < 2);

        System.out.println();
        System.out.println("Fine partita. Vince: " + getGameWinner().getName() + "\t" + getGameWinner().getTurnScore());
    }
}