package game;

public class SpecialCard extends Card {

    public String potere;
    public SpecialCard(String nome, String potere, String percorso_immagine) {

        super(nome, percorso_immagine);
        this.potere = potere;
    }

    public String getPower() {
        return potere;
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public String toString() { return getName() + "\t" + getPower()  + "\t" + getFile(); }

    @Override
    public boolean isSpecial() { return true; }

}
