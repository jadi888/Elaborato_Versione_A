import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UtilityComprensorio {
    private static final String FILE_REGIONI = "regioni.txt";
    private static final String DIRECTORY_REGIONI = "Regioni";

    public static List<String> getElencoRegioni() {
        List<String> regioni = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_REGIONI))) {
            String line;
            while ((line = reader.readLine()) != null) {
                regioni.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore durante la lettura del file delle regioni.");
        }

        return regioni;
    }

    public static Map<String, List<String>> getComuniPerRegioni() {
        Map<String, List<String>> comuniPerRegione = new HashMap<>();

        List<String> regioni = getElencoRegioni();

        for (String regione : regioni) {
            List<String> comuni = caricaComuniDaFile(regione);
            comuniPerRegione.put(regione, comuni);
        }

        return comuniPerRegione;
    }

    private static List<String> caricaComuniDaFile(String regione) {
        List<String> comuni = new ArrayList<>();
        String regioneFileName = UtilityComprensorio.DIRECTORY_REGIONI + "/" + regione + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(regioneFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                comuni.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore durante la lettura del file dei comuni per la regione " + regione);
        }

        return comuni;
    }
}
