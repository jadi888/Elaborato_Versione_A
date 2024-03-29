import java.util.List;

public class Autenticatore {
    private boolean primoAccesso = true;
    private final List<String> credenzialiRegistrati;

    public Autenticatore(List<String> credenzialiRegistrati) {
        this.credenzialiRegistrati = credenzialiRegistrati;
    }

    public boolean verificaPrimoAccesso(String inputUsername, String inputPassword, boolean isNewUser) {
        if (this.primoAccesso && isNewUser) {
            // Verifica delle credenziali per il primo accesso
            if ("configuratore".equalsIgnoreCase(inputUsername) && "password".equals(inputPassword)) {
                this.primoAccesso = false;
                return true;
            } else {
                System.out.println(Messaggi.ACCESSO_NEGATO_PRIMO_ACCESSO);
                return false;
            }
        }
        return false; // Non è il primo accesso
    }

    public boolean verificaAccessoRegistrato(String inputUsername, String inputPassword) {
        // Itera attraverso ogni riga delle credenziali registrate
        for (String riga : credenzialiRegistrati) {
            String[] parti = riga.split("\\s+", 2);
            if (parti.length == 2) {
                String storedUsername = parti[0].trim();
                String storedPassword = parti[1].trim();

                // Verifica se l'username e la password corrispondono esattamente
                if (storedUsername.equals(inputUsername) && storedPassword.equals(inputPassword)) {
                    return true; // Accesso riuscito
                }
            }
        }

        // Se nessuna corrispondenza è stata trovata, stampa un messaggio di errore
        System.out.println(Messaggi.ACCESSO_NEGATO_ERRATE);
        return false;
    }
}
