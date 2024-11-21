import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class PlantManager  {
    private List<Plant> plants = new ArrayList<>();

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public Plant get(int index) {
        return plants.get(index);
    }

    public void removePlant(int index) {
        plants.remove(index);
    }

    public void removeallPlant() {
        plants.clear();
    }

    public List<Plant> getCopyPlants() {
        return new ArrayList<>(plants);
    }

    public List<Plant> getPlantforWatering()
    {
        List<Plant> result = new ArrayList<>();
        for (Plant plant : plants)
        {
            LocalDate watering = plant.getWatering();
            LocalDate nextWatering = watering.plusDays(plant.getFrequencyOfWatering());
            if (nextWatering.isBefore(LocalDate.now()) || nextWatering.isEqual(LocalDate.now()))
            {
                result.add(plant);
            }
        }
        return result;
    }

    public List<String> getWateringInfo() {
        List<String> result = new ArrayList<>();
        for ( Plant plant : plants) {
            String name = plant.getName();
            LocalDate watering = plant.getWatering();
            LocalDate nextWatering = watering.plusDays(plant.getFrequencyOfWatering());
            {
               result.add(name + " watering:" + watering + " nextWatering:" + nextWatering + "\n");
            }
        }
            return result;
        }

    public void sort() {
        plants.sort(null);
    }

    public void sort(Comparator<Plant> comparator) {
        plants.sort(comparator);
    }

    public List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }

    public void readFromFile(String filename, String delimiter) throws PlantException {
        try (Scanner scanner = new Scanner(
                new BufferedReader(new FileReader(filename)))) {
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNumber++;
                plants.add(Plant.parse(line, lineNumber, delimiter));
            }
        } catch (FileNotFoundException e) {
            throw new PlantException(
                    "Subor " + filename + " nebol najdeny!\n" + e.getLocalizedMessage());
        }
    }

    public void saveToFile(String filename, String delimiter) throws PlantException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Plant plant : plants) {
                writer.println(plant.toFileString(delimiter));
            }
        } catch (IOException e) {
            throw new PlantException("Subor " + filename + "nebol najdeny!\n" +
                    e.getLocalizedMessage());
        }
            }
        }