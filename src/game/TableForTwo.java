package game;

import java.util.ArrayList;

public class TableForTwo extends Table implements Game{

    public TableForTwo(Player g1, Player g2) {
        this.cardOnTable = new ArrayList<>();
        this.playerInGame = new Player[]{g1, g2};
        this.playerOrder = new ArrayList<>();
        playerOrder.add(g1);
        playerOrder.add(g2);
        setEffectList();
    }

    @Override
    public void resetScores() {
        getFirst().resetPoints();
        getSecond().resetPoints();
    }

    @Override
    public void fillHand() {
        getFirst().getHand().refillHand(getFirst().getDeck());
        getSecond().getHand().refillHand(getSecond().getDeck());
    }

    @Override
    public void whoWin() {
        if (cardOnTable.get(1).getValue() > (cardOnTable.getFirst().getValue() + getEffect())) {

            Player l = playerOrder.getFirst();
            playerOrder.set(0, playerOrder.get(1));
            playerOrder.set(1, l);
        }
    }
    @Override
    public void  getTurnWinner() {
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
        else if (cardOnTable.getLast().isSpecial()) {

            if (cardOnTable.getLast().getName().equals("Malus")) {

                playerOrder.getLast().addPoints(roundTotalScore() / 2);
                Player l = playerOrder.getFirst();
                playerOrder.set(0, playerOrder.getLast());
                playerOrder.set(1, l);
            }
            else if (cardOnTable.getLast().getName().equals("Bonus")) {

                whoWin();
                playerOrder.getFirst().addPoints(0);
            }
        }
        else if (cardOnTable.getFirst().getValue() + getEffect() >= cardOnTable.getLast().getValue()) {

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

        if (getFirst().getPoints() >= getSecond().getPoints()) {
            getFirst().addTurnScore();
            System.out.println("Vince il round: " + getFirst().getName() + "\t" + getFirst().getTurnScore());
        }
        else if (getSecond().getPoints() > getFirst().getPoints()) {
            getSecond().addTurnScore();
            System.out.println("Vince il round: " + getSecond().getName() + "\t" + getSecond().getTurnScore());
        }
        else {
            System.out.println("Pareggio!");
        }
        resetScores();
    }

    @Override
    public Player getGameWinner() {

        Player winner;
        if (getFirst().getTurnScore() >= getSecond().getTurnScore()) {
            winner = getFirst();
        }
        else {
            winner = getSecond();
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
                getTurnWinner();
            }
            getRoundWinner();
            System.out.println();
        } while (getGameWinner().getTurnScore() < 2);

        System.out.println();
        System.out.println("Fine partita. Vince: " + getGameWinner().getName() + "\t" + getGameWinner().getTurnScore());
    }
}