import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class Plant implements Comparable<Plant> {
    private String name;
    private String notes;
    private int frequencyOfWatering;
    private LocalDate watering;
    private LocalDate planted;

    public Plant(String name, String notes, int frequencyOfWatering, LocalDate watering, LocalDate planted)
            throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.setWatering(watering);
        this.setFrequencyOfWatering(frequencyOfWatering);
    }

    public Plant(String name, int frequencyOfWatering) throws PlantException {
        this(name, "", frequencyOfWatering, LocalDate.now(), LocalDate.now());
    }

    public Plant(String name) throws PlantException {
        this(name, "", 7, LocalDate.now(), LocalDate.now());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        if (watering.isBefore(planted)) {
            throw new PlantException("Date of watering can not be before date of planted");
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frequency of watering can not be less than zero or equal to zero");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public String getWateringInfo() {
        String result = name + " watering: " + watering + " next watering: " + watering.plusDays(frequencyOfWatering);
        return result;
    }

    public String doWateringNow() {
        watering = LocalDate.now();
        String result = name + "\t" + notes + "\t" + frequencyOfWatering + "\t" + watering + "\t" + planted;
        return result;
    }

    @Override
    public int compareTo(Plant otherPlant) {
        return name.compareTo(otherPlant.name);
    }

    @Override
    public String toString() {
        return name + "\t" +
                notes + "\t" +
                frequencyOfWatering + "\t" +
                watering + "\t" +
                planted;
    }

    public String toFileString(String delimiter) {
        return name + delimiter +
                notes + delimiter +
                frequencyOfWatering + delimiter +
                watering + delimiter +
                planted;
    }

    public static Plant parse(String line, int lineNumber, String delimiter) throws PlantException {
        int itemsRequired = 5;
        String[] parts = line.split(delimiter);
        if (parts.length != itemsRequired) {
            throw new PlantException(
                    "Nespravny pocet poloziek na riadku cislo: " + lineNumber + " Ocakavame " + itemsRequired +
                            " poloziek");
        }
        String name = parts[0].trim();
        String notes = parts[1].trim();
        try {
            int frequencyOfWatering = Integer.parseInt(parts[2].trim());
            LocalDate watering = LocalDate.parse(parts[3].trim());
            LocalDate planted = LocalDate.parse(parts[4].trim());
            return new Plant(name, notes, frequencyOfWatering, watering, planted);
        } catch (NumberFormatException | DateTimeParseException e) {
            throw new PlantException("Chybny format cisla/datumu na riadku cislo: " + lineNumber);
        }
    }
}





