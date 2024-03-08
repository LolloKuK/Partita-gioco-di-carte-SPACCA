package game;

public abstract class Card {

    public String nome;
    public String percorso_immagine;

    public Card(String nome, String percorso_immagine){
        this.nome = nome;
        this.percorso_immagine = percorso_immagine;
    }

    public String getName() {
        return nome;
    }
    public String getFile() {
        return percorso_immagine;
    }

    public abstract int getValue();
    public abstract String toString();
    public abstract boolean isSpecial();
}