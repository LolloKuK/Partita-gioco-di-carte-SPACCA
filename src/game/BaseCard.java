package game;

public class BaseCard extends Card {

    public int numero;
    public BaseCard(String nome, int numero, String percorso_immagine) {
        super(nome, percorso_immagine);
        this.numero = numero;
    }

    @Override
    public int getValue() {
        return numero;
    }

    @Override
    public String toString() {
        return getName() + "\t" + getValue() + "\t" + getFile();
    }

    @Override
    public boolean isSpecial() { return false; }
}
