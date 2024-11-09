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
            //Plant plant = new Plant("orchidea", "", -9, LocalDate.of(2024, 10, 30), LocalDate.of(2024, 10, 20));
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
        manager.readFromFile("resources/kvetiny.txt", DELIMITER);
        //manager.getPlants1().forEach(System.out::println);
    }
    //private static void fillPlants (PlantManager manager) {}


}