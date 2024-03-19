package game;

public abstract class Player {

    public String nome;
    public Hand mano;
    public Deck mazzo;
    public int points;
    public int turnScore;

    public Player(String nome, Deck mazzo) {

        this.nome = nome;
        this.mazzo = mazzo;
        this.mano = new Hand();

        this.points = 0;
        this.turnScore = 0;
    }

    public String getName() { return nome; }
    public Hand getHand() { return mano; }
    public Deck getDeck() { return mazzo; }

    public void addPoints(int s) { this.points += s; }
    public int getPoints() { return points; }
    public void resetPoints() { this.points = 0; }

    public void addTurnScore() { this.turnScore++; }
    public int getTurnScore() { return turnScore; }

    public Card playCard(int i) { return mano.getOneCard(i); }
}