import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;


//sprava zoznamu kvetin___:
public class PlantManager {
    private Plant plant;
    private List<Plant> plants1 = new ArrayList<>();
    private List<Plant> plants2 = new ArrayList<>();
    private List<Plant> plants3 = new ArrayList<>();

    //vytvorenie metod_:
    public void addPlant(Plant plant) {
        plants1.add(plant);
    }

    public void removePlant(Plant plant) {
        plants1.remove(plant);
    }

    public Plant get(int index) {
        return plants1.get(index);
    }

    //ziskani kopie seznamu kvetin:
    public List<Plant> getCopyPlants1() {
        return new ArrayList<>(plants1);
    }
    //metoda,ktera vrati seznam rostlin, ktere je treba polievat:

    public List<Plant> getPlantWithNextWatering(String name) {
        List<Plant> result = new ArrayList<>();
        for (Plant plant : plants1) {
            LocalDate nextWatering = plant.getWatering().plusDays(plant.getFrequency_of_watering());
            if (nextWatering.isEqual(LocalDate.now())) {
                result.add(plant);
            }
        }
        return result;
    }

    //zoradit rastliny v zozname:
    public void sort() {
        plants1.sort(null);
    }

    public void sort(Comparator<Plant> comparator) {
        plants1.sort(comparator);
    }
    //tato metoda suvisi s metodou demoReadFromFile:
    public List<Plant> getPlants1() {
        return plants1;
    }

    // vytvor metodu pre nacitanie kvetin zo suboru:
    public void readFromFile(String filename, String delimiter) throws PlantException {
        try (Scanner scanner = new Scanner(
                new BufferedReader(new FileReader(filename)))) {
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //String delimiter = "\t";
                lineNumber++;
                System.out.println(Plant.parse(line, lineNumber, delimiter));
            }
        } catch (FileNotFoundException e) {
            throw new PlantException(
                    "Subor " + filename + " nebol najdeny!\n" + e.getLocalizedMessage());
        }
    }
}