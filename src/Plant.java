import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//vytvorenie triedy Plant a jej atributov:
public class Plant implements Comparable<Plant> {
    private String name;
    private String notes;
    private int frequency_of_watering;
    private LocalDate watering;
    private LocalDate planted;


    //vytvorenie 3 konstruktorov:
    //1.konstruktor pre nastavenie vsetkych atributov__:
    public Plant(String name, String notes, int frequency_of_watering, LocalDate watering, LocalDate planted)
            throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.setWatering(watering);
        this.setFrequency_of_watering(frequency_of_watering);

    }

    //2.konstruktor nastavi ako notes prazdny retazec a datumy na dnesny datum:
    public Plant(String name, int frequency_of_watering) throws PlantException {
        this(name, "", frequency_of_watering, LocalDate.now(), LocalDate.now());
    }

    //3.konstruktor = predosly + vychodzia frekvencia zalievky 7 dni
    // uzivatel teda zada iba nazov rastliny:
    public Plant(String name) throws PlantException {
        this(name, "", 7, LocalDate.now(), LocalDate.now());
    }
    //vytvor vychodzi pristupove metody pro vsechny atributy:

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

    //osetrenie chyboveho stavu:

    public void setWatering(LocalDate watering) throws PlantException {
        if (watering.isBefore(planted)) {
            throw new PlantException("Date of watering can not be before date of planted" );
        }
        this.watering = watering;
    }

    public int getFrequency_of_watering() {
        return frequency_of_watering;
    }

    //osetrenie chyboveho stavu:

    public void setFrequency_of_watering(int frequency_of_watering) throws PlantException {
        if (frequency_of_watering <= 0) {
            throw new PlantException("Frequency of watering can not be less than zero or equal to zero" );
        }
        this.frequency_of_watering = frequency_of_watering;
    }

    //priprav metodu getWateringinfo, ktora vrati textovu info obsahujucu nazov
    //kvetiny, datum poslednej zalievky, datum doporucenej dalsej zalievky:
    public String getWateringInfo() {
        String result = name + " watering: " + watering + " next watering: " + watering.plusDays(frequency_of_watering);
        return result;
    }

    //priprav metodu doWateringNow, ktora nastavi watering na dnesny den:
    public LocalDate doWateringNow() {
        watering = LocalDate.now();
        return watering;
    }

    //zoradovanie podla nazvu rastliny ako vychodzia varianta zoradovania rastlin:
    @Override
    public int compareTo(Plant otherPlant) {
        return name.compareTo(otherPlant.name);

    }
    //potrebna metoda pre spravne nacitanie obsahu suboru (Generate-toString) a
    // vygenerovany text upravit:

    @Override
    public String toString () {
        return name + "/t" +
                notes + "/t" +
                frequency_of_watering + "/t" +
                watering + "/t" +
                planted;}

    //potrebna metoda pre zapis do suboru:
    public String toFileString (String delimiter) {
        return name + delimiter +
                notes + delimiter +
                frequency_of_watering + delimiter +
                watering + delimiter +
                planted;}

    //vytvorenie metody parse:
    public static Plant parse(String line, int lineNumber, String delimiter) throws PlantException {
        int itemsRequired = 5;
        String[] parts = line.split(delimiter);
        if (parts.length != itemsRequired) {
            throw new PlantException(
                    "Nespravny pocet poloziek na riadku cislo: " + lineNumber + " Ocakavame " + itemsRequired +
                            " poloziek" );
        }
        String name = parts[0].trim();
        String notes = parts[1].trim();
        try {
            int frequency_of_watering = Integer.parseInt(parts[2]);
            LocalDate watering = LocalDate.parse(parts[3].trim());
            LocalDate planted = LocalDate.parse(parts[4].trim());
            return new Plant(name, notes, frequency_of_watering, watering, planted);
        } catch (NumberFormatException | DateTimeParseException e) {
            throw new PlantException("Chybny format cisla/datumu na riadku cislo: " + lineNumber);
        }
    }

}
