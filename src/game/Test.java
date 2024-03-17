package game;

public class Test {

    public static void main(String[] args) {
        // Creo i 4 mazzi differenti
        String sonyFile = "mazzo-sony.txt";
        Deck mazzo_sony = new Deck(sonyFile);
        String comicsFile = "mazzo-comics.txt";
        Deck mazzo_comics = new Deck(comicsFile);
        String animeFile = "mazzo-anime.txt";
        Deck mazzo_anime = new Deck(animeFile);
        String retroFile = "mazzo-retro.txt";
        Deck mazzo_retro = new Deck(retroFile);

        // Stampo i 4 mazzi
        //mazzo_sony.printDeck();
        //mazzo_anime.printDeck();
        //mazzo_retro.printDeck();
        //mazzo_comics.printDeck();

        // Prova di mescola di ogni mazzo
        mazzo_sony.shuffleDeck();
        //mazzo_sony.printDeck();
        mazzo_retro.shuffleDeck();
        mazzo_comics.shuffleDeck();
        System.out.println();

        // Creo 3 giocatori tipo con 3 mazzi differenti
        Player giocatore1 = new Giocatore("Fede", mazzo_comics);
        Player giocatore2 = new Giocatore("Lollo", mazzo_sony);
        Player giocatore3 = new Giocatore("Fra", mazzo_retro);
        Player bot = new IA("Bot 1", mazzo_anime);

        // Riempio la mano dei giocatori e le stampo
        /*
        giocatore1.getHand().refillHand(giocatore1.getDeck());
        giocatore1.getHand().printHand();
        System.out.println();
        giocatore2.getHand().refillHand(giocatore2.getDeck());
        giocatore2.getHand().printHand();
        System.out.println();
        giocatore3.getHand().refillHand(giocatore3.getDeck());
        giocatore3.getHand().printHand();
        */

        // Set di un tavolo da due giocatori
        TableForTwo tavolo2 = new TableForTwo(giocatore1, giocatore2);
        TableForThree tavolo3 = new TableForThree(giocatore1, giocatore2, giocatore3);
        TableForFour tavolo4 = new TableForFour(giocatore1, giocatore2, giocatore3, bot);
        // turni di prova
        /*
        tavolo.addOnTable(tavolo.getFirst(), 1);
        tavolo.addOnTable(tavolo.getLast(), 4);
        tavolo.printTable();

        tavolo.addOnTable(tavolo.getFirst(), 4);
        tavolo.addOnTable(tavolo.getLast(), 2);
        tavolo.printTable();

        tavolo.addOnTable(tavolo.getFirst(), 3);
        tavolo.addOnTable(tavolo.getLast(), 1);
        tavolo.printTable();

        tavolo.addOnTable(tavolo.getFirst(), 1);
        tavolo.addOnTable(tavolo.getLast(), 2);
        tavolo.printTable();

        tavolo.addOnTable(tavolo.getFirst(), 1);
        tavolo.addOnTable(tavolo.getLast(), 1);
        tavolo.printTable();
         */

        //tavolo2.matchStart();
        //tavolo3.matchStart();
        tavolo4.matchStart();
    }
}
