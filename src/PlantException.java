//Osetrenie nespravneho vstupu:
//Vytvor novu triedu vynimok s nazvom PlantException, bude potomkom (extends)
//triedy Exception___,
public class PlantException extends Exception {
    public PlantException(String message) {
        super(message);
    }
}