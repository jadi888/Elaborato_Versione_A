import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuComprensorio {
    private final Scanner scanner;

    public MenuComprensorio() {
        this.scanner = new Scanner(System.in);
    }

    public void creaComprensorioDaConfiguratore() {
        // Ottieni l'elenco delle regioni dal configuratore
        List<String> regioni = UtilityComprensorio.getElencoRegioni();

        // Visualizza l'elenco delle regioni
        System.out.println("Scegli una regione:");
        for (int i = 0; i < regioni.size(); i++) {
            System.out.println((i + 1) + ". " + regioni.get(i));
        }

        // Chiedi all'utente di scegliere una regione
        System.out.print("Inserisci il numero corrispondente: ");
        int sceltaRegione = scanner.nextInt();
        scanner.nextLine(); // Consuma il newline residuo dopo nextInt()

        if (sceltaRegione < 1 || sceltaRegione > regioni.size()) {
            System.out.println("Scelta non valida. Operazione annullata.");
            return;
        }

        String nomeComprensorio = regioni.get(sceltaRegione - 1);

        // Ottieni l'elenco dei comuni per la regione selezionata dal configuratore
        Map<String, List<String>> comuniPerRegione = UtilityComprensorio.getComuniPerRegioni();

        // Crea il nuovo comprensorio
        ComprensorioGeografico nuovoComprensorio = new ComprensorioGeografico();
        nuovoComprensorio.setNome(nomeComprensorio);

        // Scelta dei comuni per la regione
        List<String> comuniScelti = scegliComuni(nomeComprensorio, comuniPerRegione);
        if (comuniScelti.isEmpty()) {
            System.out.println("Operazione annullata.");
            return;
        }

        // Aggiunta dei comuni al nuovo comprensorio
        nuovoComprensorio.setComuni(comuniScelti);

        chiediNomeComprensorio(nuovoComprensorio);
        mostraInformazioniComprensorio(nuovoComprensorio);
        salvaComprensorioSuFile(nuovoComprensorio);

    }

    private List<String> scegliComuni(String regione, Map<String, List<String>> comuniPerRegione) {
        System.out.println("Scegli fino a 3 comuni per la regione " + regione + ":");

        List<String> comuni = comuniPerRegione.get(regione);

        for (int i = 0; i < comuni.size(); i++) {
            System.out.println((i + 1) + ". " + comuni.get(i));
        }

        System.out.print("Inserisci i numeri corrispondenti separati da virgola (es. 1,2,3) o 0 per annullare: ");
        String scelta = scanner.nextLine();

        if (scelta.equals("0")) {
            return new ArrayList<>(); // Annulla l'operazione
        }

        String[] numeriScelti = scelta.split(",");
        List<String> comuniScelti = new ArrayList<>();

        for (String numero : numeriScelti) {
            int indice = Integer.parseInt(numero.trim()) - 1;
            if (indice >= 0 && indice < comuni.size()) {
                comuniScelti.add(comuni.get(indice));
            }
        }

        return comuniScelti;
    }

    public void chiediNomeComprensorio(ComprensorioGeografico comprensorio) {
        System.out.print("Inserisci un nome per il comprensorio: ");
        String nomeComprensorio = scanner.nextLine();
        comprensorio.setNome(nomeComprensorio);
    }

    public void mostraInformazioniComprensorio(ComprensorioGeografico comprensorio) {
        System.out.println("Il comprensorio \"" + comprensorio.getNome() + "\" contenente i comuni " +
                comprensorio.getComuni() + " è stato creato.");
    }

    public void salvaComprensorioSuFile(ComprensorioGeografico comprensorio) {
        String nomeFile = comprensorio.getNome().replace(" ", "_") + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeFile))) {
            writer.println("Nome del Comprensorio: " + comprensorio.getNome());
            writer.println("Comuni:");
            for (String comune : comprensorio.getComuni()) {
                writer.println(comune);
            }
            System.out.println(Messaggi.SALVATAGGIO_COMPRENSORIO_SUCCESSO + nomeFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(Messaggi.ERRORE_SALVATAGGIO_COMPRENSORIO);
        }
    }

}
