import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Campo {
    private String nome;
    private List<String> dominio;
    private Map<String, String> descrizioni;

    public Campo(String nome, List<String> dominio, Map<String, String> descrizioni) {
        this.nome = nome;
        this.dominio = dominio;
        this.descrizioni = descrizioni;
    }

    public String getNome() {
        return nome;
    }

    public List<String> getDominio() {
        return dominio;
    }

    public String getDominioODescrizione() {
        if (dominio != null && !dominio.isEmpty()) {
            return "Dominio: " + String.join(", ", dominio);
        } else {
            return "Il campo \"" + nome + "\" non ha un dominio.";
        }
    }

    public void aggiungiDescrizione(String valore, String descrizione) {
        if (dominio.contains(valore)) {
            descrizioni.put(valore, descrizione);
        } else {
            throw new IllegalArgumentException("Il valore non appartiene al dominio del campo.");
        }
    }

    public String getDescrizione(String valore) {
        return descrizioni.get(valore);
    }

    @Override
    public String toString() {
        return "Campo{" +
                "nome='" + nome + '\'' +
                ", dominio=" + dominio +
                ", descrizioni=" + descrizioni +
                '}';
    }


}