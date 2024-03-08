package game;

public class Game {

    public Table table;

    public Game(Table tavolo) {
        this.table = tavolo;
    }

    //public Table getTable() { return table; }

    public void matchStart() {
        do {
            table.fillHand();
            for (int i = 0; i < 5; i++) {
                table.addOnTable(table.getFirst(), 1);
                table.addOnTable(table.getLast(), 1);
                table.getTurnWinner();
            }
            getRoundWinner();
            System.out.println();
        } while (getGameWinner().getTurnScore() < 2);
        System.out.println();
        System.out.println("Fine partita. Vince: " + getGameWinner().getName() + "\t" + getGameWinner().getTurnScore());
    }

    public void getRoundWinner() {

        if (table.getFirst().getPoints() > table.getLast().getPoints()) {
            table.getFirst().addTurnScore();
            System.out.println("Vince il round: " + table.getFirst().getName() + "\t" + table.getFirst().getTurnScore());
        }
        else if (table.getFirst().getPoints() == table.getLast().getPoints()) {
            System.out.println("Pareggio!");
        }
        else {
            table.getLast().addTurnScore();
            System.out.println("Vince il round: " + table.getLast().getName() + "\t" + table.getLast().getTurnScore());
        }
        table.resetScores();
    }

    public Player getGameWinner() {

        Player winner;
        if (table.getFirst().getTurnScore() >= table.getLast().getTurnScore()) {
            winner = table.getFirst();
        }
        else {
            winner = table.getLast();
        }
        return winner;
    }
}
