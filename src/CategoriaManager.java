import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class CategoriaManager {
    private Scanner scanner;
    private List<CategoriaBase> categorie = new ArrayList<>();

    public CategoriaManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public CategoriaBase creaNuovaCategoria() {
        System.out.print("Inserisci il nome della nuova gerarchia: ");
        String nomeGerarchia = scanner.nextLine();

        boolean vuoleCampoCaratteristico = chiediSeVuoleCampoCaratteristico();

        CategoriaBase nuovaGerarchia;
        if (vuoleCampoCaratteristico) {
            nuovaGerarchia = creaNuovaCategoriaNonFoglia(nomeGerarchia);
        } else {
            nuovaGerarchia = new CategoriaFoglia(nomeGerarchia);
        }
        return nuovaGerarchia;
    }

    private boolean chiediSeVuoleCampoCaratteristico() {
        System.out.print("Vuoi dotare la gerarchia di un campo caratteristico? (1. Sì / 2. No): ");
        int risposta = scanner.nextInt();
        scanner.nextLine();
        return risposta == 1;
    }

    public CategoriaBase creaNuovaCategoriaNonFoglia(String nomeGerarchia) {
        System.out.print("Inserisci il nome del campo caratteristico: ");
        String nomeCampo = scanner.nextLine();
        System.out.print("Inserisci il dominio finito di valori discreti separati da virgola: ");
        String dominioString = scanner.nextLine();
        List<String> dominio = List.of(dominioString.split("\\s*,\\s*"));

        Campo campo = creaCampoConDescrizioni(nomeCampo, dominio);

        return new CategoriaNonFoglia(nomeGerarchia, campo);
    }

    private Campo creaCampoConDescrizioni(String nomeCampo, List<String> dominio) {
        System.out.println("Per ciascun valore nel dominio, inserisci una descrizione (opzionale): ");
        Map<String, String> descrizioni = new HashMap<>();

        for (String valore : dominio) {
            System.out.print("Descrizione per \"" + valore + "\": ");
            String descrizione = scanner.nextLine();
            descrizioni.put(valore, descrizione);
        }
        return new Campo(nomeCampo, dominio, descrizioni);
    }

    public void gestisciCreazioneCategorie() {
        do {
            CategoriaBase nuovaCategoria = creaNuovaCategoria();
            categorie.add(nuovaCategoria);

            System.out.print("Vuoi aggiungere un'altra categoria? (1. Sì / 2. No): ");
            int risposta = scanner.nextInt();
            scanner.nextLine();

            if (risposta != 1) {
                break;
            }
        } while (true);

        salvaCategorieSuFile("categorie.txt");
    }

    public void salvaCategorieSuFile(String nomeFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeFile))) {
            for (CategoriaBase categoria : categorie) {
                writer.println(categoria.toString());
            }
            System.out.println("Categorie salvate con successo in " + nomeFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore durante il salvataggio delle categorie su file.");
        }
    }

    public void visualizzaAlberoGerarchie() {
        // Implementa la visualizzazione dell'albero delle gerarchie
        // Utilizza i metodi toString delle classi per una rappresentazione chiara
    }
}