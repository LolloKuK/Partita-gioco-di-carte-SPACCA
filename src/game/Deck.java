package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    public ArrayList<Card> mazzo = new ArrayList <>();
    public Deck(String nomeFile) {
        createDeck(nomeFile);
    }

    public void createDeck(String nomeFile) {

        String pathFile = "/Users/lorenzocuoco/Desktop/Cartelle/IntelliJ-workspace/Partita/src/saved_data/" + nomeFile;
        //da cambiare nel caso si usi su un'altro pc
        System.out.println(pathFile);
        try (BufferedReader reader = new BufferedReader(new FileReader(pathFile))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < 15) {
                String[] parts = line.split(",");

                if (mazzo.size() < 13) {
                    String nome = parts[0].trim();
                    int numero = Integer.parseInt(parts[1].trim());
                    String percorso = parts[2].trim();

                    Card card = new BaseCard(nome, numero, percorso);
                    mazzo.add(card);
                }
                else {
                    String nome = parts[0].trim();
                    String percorso = parts[1].trim();
                    String effetto = parts[2].trim();

                    Card card = new SpecialCard(nome, percorso, effetto);
                    mazzo.add(card);
                }

                i++;
            }
        }
        catch (IOException e) { System.out.println("File non trovato"); }
    }

    public void shuffleDeck() { Collections.shuffle(mazzo); }

    public Card getFirstCard() {
        if(mazzo.isEmpty()) {
            System.out.println("Non ci sono altre carte nel deck");
            return null;
        }
        else {
            Card primaCarta = mazzo.getFirst();
            mazzo.removeFirst();
            mazzo.add(primaCarta);
            return primaCarta;
        }
    }

    /*
    public void printDeck() {
        for(int i = 0; i < this.mazzo.size(); i++) {
            System.out.println(mazzo.get(i).toString());
        }
    }
    */
}
