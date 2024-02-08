import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrazioneCredenziali {
    private final List<String> usernameRegistrati = caricaUsernameDaFile();

    public RegistrazioneCredenziali() {
    }

    public boolean registraCredenziali(String username, String password) {
        if (!isUsernameUnico(username)) {
            System.out.println("Username già utilizzato. Scegli un altro username.");
            return false;
        } else {
            salvaCredenzialiSuFile(username, password);
            usernameRegistrati.add(username + " " + password);
            return true;
        }
    }

    private boolean isUsernameUnico(String username) {
        return this.usernameRegistrati.stream().noneMatch(c -> {
            String storedUsername = c.split("\\s+", 2)[0].trim();
            return storedUsername.equals(username);
        });
    }

    private List<String> caricaUsernameDaFile() {
        ArrayList<String> usernames = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("credenziali.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+", 2);
                if (parts.length == 2) {
                    usernames.add(parts[0].trim() + " " + parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println(Messaggi.ERRORE_CARICAMENTO_UTENTI);
            e.printStackTrace();
        }

        return usernames;
    }

    private void salvaCredenzialiSuFile(String username, String password) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("credenziali.txt", true))) {
            writer.println(username + " " + password);
        } catch (IOException e) {
            System.err.println(Messaggi.ERRORE_SALVATAGGIO_CREDENZIALI);
            e.printStackTrace();
        }
    }
}