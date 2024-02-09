import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VisualizzatoreGerarchie {
    private List<CategoriaBase> categorie;

    public VisualizzatoreGerarchie(List<CategoriaBase> categorie) {
        this.categorie = categorie;
    }

    public VisualizzatoreGerarchie() {
        this.categorie = new ArrayList<>();
        leggiFileCategorie("categorie.txt");
    }

    public void visualizzaAlberoGerarchie() {
        if (categorie.isEmpty()) {
            System.out.println("Non ci sono categorie presenti al momento.");
        } else {
            for (CategoriaBase radice : trovaRadici()) {
                visualizzaSottocategorie(radice, 0);
            }
        }

        chiediSeTornareIndietro();
    }

    private List<CategoriaBase> trovaRadici() {
        List<CategoriaBase> radici = new ArrayList<>();
        for (CategoriaBase categoria : categorie) {
            if (categoria.getSottocategorie().isEmpty()) {
                radici.add(categoria);
            }
        }
        return radici;
    }

    private void visualizzaSottocategorie(CategoriaBase categoria, int livello) {
        StringBuilder spazi = new StringBuilder();
        spazi.append("  ".repeat(Math.max(0, livello)));

        System.out.println(spazi + "- " + categoria.getNome());

        if (categoria instanceof CategoriaNonFoglia) {
            Campo campo = ((CategoriaNonFoglia) categoria).getCampo();
            System.out.println(spazi + "  Campo: " + campo.getNome() + " (" + String.join(", ", campo.getDominio()) + ")");

            // Mostra le descrizioni dei valori del dominio
            for (String valore : campo.getDominio()) {
                String descrizione = campo.getDescrizione(valore);
                System.out.println(spazi + "    - " + valore + ": " + (descrizione != null ? descrizione : "Nessuna descrizione disponibile"));
            }
        }

        for (CategoriaBase sottocategoria : categoria.getSottocategorie()) {
            visualizzaSottocategorie(sottocategoria, livello + 1);
        }
    }

    private void chiediSeTornareIndietro() {
        System.out.print("Vuoi tornare indietro? (1. Sì / 2. No): ");
        Scanner scanner = new Scanner(System.in);
        int risposta = scanner.nextInt();
        scanner.nextLine(); // Consuma il newline residuo dopo nextInt()

        if (risposta != 1) {
            System.out.println("Chiusura definitiva del programma.");
            System.exit(0);
        }
    }

    private void leggiFileCategorie(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Implementa la logica per leggere il file e costruire la struttura dell'albero delle gerarchie
            // ...
        } catch (IOException e) {
            System.err.println("Errore durante la lettura del file delle categorie.");
            e.printStackTrace();
        }
    }
}
