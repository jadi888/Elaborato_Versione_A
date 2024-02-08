import java.util.List;

public class Configuratore {
    private final Autenticatore autenticatore;

    public Configuratore(List<String> credenzialiRegistrati) {
        this.autenticatore = new Autenticatore(credenzialiRegistrati);
    }

    public Autenticatore getAutenticatore() {
        return this.autenticatore;
    }

    // Aggiungi altri metodi specifici del Configuratore, se necessario
}
