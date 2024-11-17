import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String DELIMITER = "\t";

    public static void main(String[] args) {
        try {
            testCode();
            demoException();
        } catch (PlantException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(); //vypise detailnejsi popis chyby
            }
    }

    private static void demoException() throws PlantException {
        Plant plant = new Plant("kaktus", "note", 7,
                LocalDate.of(2024, 11, 9), LocalDate.of(2024, 11, 2));
        //System.out.println(plant.getWateringInfo());
        //System.out.println(plant.doWateringNow());

    }

    private static void testCode() throws PlantException {
        PlantManager manager = new PlantManager();
        //manager.readFromFile("resources/kvetiny-spatne-datum.txt", DELIMITER);
        //manager.readFromFile("resources/kvetiny-spatne-frekvence.txt", DELIMITER);
        manager.readFromFile("resources/kvetiny.txt", DELIMITER);
        manager.getPlants1().forEach(System.out::println);
        //ziskanie kvetiny na indexu cislo 2:
        //System.out.println(manager.get(2));
        System.out.println();

        System.out.println("Informacie o zalievke pre vsetky kvetiny zo zoznamu:");
        manager.getWateringInfo_().forEach(System.out::println);
        System.out.println();

        //pridaj novu kvetinu do zoznamu_:
        manager.addPlant(new Plant("Margaretka", "oblubeny kvet", 13,
                LocalDate.of(2024, 11, 18), LocalDate.of(2024, 11, 5)));
        manager.addPlant(new Plant("Tulipan na prodej 1", 14));
        manager.addPlant(new Plant("Tulipan na prodej 2", 14));
        manager.addPlant(new Plant("Tulipan na prodej 3", 14));
        manager.addPlant(new Plant("Tulipan na prodej 4", 14));
        manager.addPlant(new Plant("Tulipan na prodej 5", 14));
        manager.addPlant(new Plant("Tulipan na prodej 6", 14));
        manager.addPlant(new Plant("Tulipan na prodej 7", 14));
        manager.addPlant(new Plant("Tulipan na prodej 8", 14));
        manager.addPlant(new Plant("Tulipan na prodej 9", 14));
        manager.addPlant(new Plant("Tulipan na prodej 10", 14));

        //kvetinu na 3.pozici odober zo zoznamu:
        manager.removePlant(2);

        //uloz zoznam kvetin do noveho suboru:
        manager.saveToFile("resources/kvetiny2.txt", DELIMITER);

        //opatovne nacitanie vygenerovaneho suboru:
        manager.removeallPlant();
        manager.readFromFile("resources/kvetiny2.txt", DELIMITER);
        manager.getPlants1().forEach(System.out::println);

        //zoradenie rastlin podla roznych kriterii a vypis:
        manager.sort(Comparator.comparing(Plant::getName)
                .thenComparing(Plant::getWatering));
        System.out.println();
        System.out.println("Zoradene podla nazvu rastliny a datumu poslednej zalievky:");
        manager.getPlants1().forEach(System.out::println);

        manager.sort(Comparator.comparing(Plant::getPlanted));
        System.out.println();
        System.out.println("Zoradene podla datumu zasadenia rastliny:");
        manager.getPlants1().forEach(System.out::println);

        //System.out.println();
        //System.out.println("zoznam rastlin ktore je treba polievat:");
        //System.out.println(manager.getPlantforWatering());

    }
}