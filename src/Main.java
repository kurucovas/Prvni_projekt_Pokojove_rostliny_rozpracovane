import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //private static final String FILENAME = "resources/kvetiny.txt";
    private static final String DELIMITER = "\t";

    public static void main(String[] args) {
        try {
            //Plant plant = new Plant("orchidea", "", 10, LocalDate.of(2024, 10, 30), LocalDate.of(2024, 10, 20));
            //PlantManager manager = new PlantManager();
            //manager.sort(Comparator.comparing(Plant::getName).thenComparing(Plant::getWatering));
            //plant.setFrequency_of_watering(7);
            //plant.setWatering(LocalDate.of(2024, 11, 3));
            demoReadFile();
        } catch (PlantException e) {
            System.err.println(e.getMessage());
        }
    }
        private static void demoReadFile() throws PlantException {
            PlantManager manager = new PlantManager();
            Plant plant = new Plant("kaktus");
            manager.readFromFile("resources/kvetiny.txt", DELIMITER);
            //manager.getPlants1().forEach(System.out::println);
            //System.out.println("Informacie o zalievke pre vsetky kvetiny " +
                   // "zo zoznamu: " + manager.getWateringInfo());
            //pridaj novu kvetinu do zoznamu:
            manager.addPlant(new Plant("margaretka", "oblubeny kvet", 7, LocalDate.of(2024, 11, 9), LocalDate.of(2024, 11, 2)));
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
            //manager.readFromFile("resources/kvetiny2.txt", DELIMITER);
            //manager.getPlants1().forEach(System.out::println);
            //zoradenie rastlin podla roznych kriterii a vypis:












    }
    //private static void fillPlants (PlantManager manager)__ {}





}