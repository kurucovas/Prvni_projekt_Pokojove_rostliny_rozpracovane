import java.io.*;
import java.time.LocalDate;
import java.util.*;


//sprava zoznamu kvetin:
public class PlantManager  {
    private Plant plant;
    private List<Plant> plants1 = new ArrayList<>();
    private List<Plant> plants2 = new ArrayList<>();
    private List<Plant> plants3 = new ArrayList<>();

    //vytvorenie metod:

    public void addPlant(Plant plant) {
        plants1.add(plant);
    }

    public Plant get(int index) {
        return plants1.get(index);
    }

    public void removePlant(int index) {
        plants1.remove(index);
    }

    public void removeallPlant () {
        plants1.clear();
    }

    //metoda pre ziskanie kopie zoznamu kvetin:
    public List<Plant> getCopyPlants1() {
        return new ArrayList<>(plants1);
    }

    //metoda, ktora vrati zoznam rastlin, ktore je treba polievat:
    public List<Plant> getPlantforWatering()
    {
        List<Plant> result = new ArrayList<>();
        int i = 0;
        for (Plant plant : plants1)
        {
            LocalDate watering = plants1.get(i).getWatering();
            LocalDate nextWatering = watering.plusDays(plants1.get(i).getFrequency_of_watering());
            if (nextWatering.isBefore(LocalDate.now()) || nextWatering.isEqual(LocalDate.now()))
            {
                result.add(plant);
            }
            i++;
        }
        return result;
    }

    //metoda, ktora vrati informacie o zalievke kvetin:
    public List<String> getWateringInfo_ () {
        List<String> result = new ArrayList<>();
        int i = 0;
        for ( Plant plant : plants1 ) {

            String name = plants1.get(i).getName();
            LocalDate watering = plants1.get(i).getWatering();
            LocalDate nextWatering = watering.plusDays(plants1.get(i).getFrequency_of_watering());

            {
               result.add(name + " watering:" + watering + " nextWatering:" + nextWatering);
                i++;
            }
        }
            return result;
        }

    //metody pre zoradenie rastlin v zozname (treba pridat aj "implements Comparable" do public class Plant):
    public void sort () {
        plants1.sort(null);
    }

    public void sort(Comparator<Plant> comparator) {
        plants1.sort(comparator);
    }

    //metoda, ktora vrati zoznam rastlin:
    public List<Plant> getPlants1() {
        return new ArrayList<>(plants1);
    }

    // vytvor metodu pre nacitanie kvetin zo suboru (metoda String toString v subore Plant.java):
    public void readFromFile(String filename, String delimiter) throws PlantException {
        try (Scanner scanner = new Scanner(
                new BufferedReader(new FileReader(filename)))) {
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //String delimiter = "\t";
                lineNumber++;
                plants1.add(Plant.parse(line, lineNumber, delimiter));
            }
        } catch (FileNotFoundException e) {
            throw new PlantException(
                    "Subor " + filename + " nebol najdeny!\n" + e.getLocalizedMessage());
        }
    }
    //vytvor metodu pre ulozenie kvetin do suboru (metoda String toFileString v subore Plant.java):
    public void saveToFile(String filename, String delimiter) throws PlantException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Plant plant : plants1) {
                writer.println(plant.toFileString(delimiter));
            }
        } catch (IOException e) {
            throw new PlantException("Subor " + filename + "nebol najdeny!\n" +
                    e.getLocalizedMessage());
        }
            }
        }