import java.time.LocalDate;
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
            e.printStackTrace();
            }
    }

    private static void demoException() throws PlantException {
        Plant plant = new Plant("kaktus", "note", 7,
                LocalDate.of(2024, 11, 9), LocalDate.of(2024, 11, 2));
    }

    private static void testCode() throws PlantException {
        PlantManager manager = new PlantManager();
        manager.readFromFile("resources/kvetiny.txt", DELIMITER);
        manager.getPlants().forEach(System.out::println);
        System.out.println();

        System.out.println("Informacie o zalievke pre vsetky kvetiny zo zoznamu:");
        manager.getWateringInfo().forEach(System.out::println);
        System.out.println();

        manager.addPlant(new Plant("Margaretka", "oblubeny kvet", 13,
                LocalDate.of(2024, 11, 18), LocalDate.of(2024, 11, 5)));

        int numberOfFlowers = 10;
        for (int i = 1; i <= numberOfFlowers; i++)
        {
            manager.addPlant(new Plant( "Tulipan na prodej "+i, 14));
        }
        manager.removePlant(2);

        manager.saveToFile("resources/kvetiny2.txt", DELIMITER);

        manager.removeallPlant();
        manager.readFromFile("resources/kvetiny2.txt", DELIMITER);
        manager.getPlants().forEach(System.out::println);

        manager.sort(Comparator.comparing(Plant::getName)
                .thenComparing(Plant::getWatering));
        System.out.println();
        System.out.println("Zoradene podla nazvu rastliny a datumu poslednej zalievky:");
        manager.getPlants().forEach(System.out::println);

        manager.sort(Comparator.comparing(Plant::getPlanted));
        System.out.println();
        System.out.println("Zoradene podla datumu zasadenia rastliny:");
        manager.getPlants().forEach(System.out::println);
    }
}