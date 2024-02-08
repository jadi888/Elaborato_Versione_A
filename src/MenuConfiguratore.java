import java.util.List;
import java.util.Scanner;

public class MenuConfiguratore {
    private final Scanner scanner;
    private final Configuratore configuratore;
    private final RegistrazioneCredenziali registrazioneCredenziali;

    public MenuConfiguratore(List<String> credenzialiRegistrati) {
        this.scanner = new Scanner(System.in);
        this.configuratore = new Configuratore(credenzialiRegistrati);
        this.registrazioneCredenziali = new RegistrazioneCredenziali();
    }

    public void avviaMenu() {
        System.out.println("*******************************************");
        System.out.println("*           Benvenuto Configuratore         *");
        System.out.println("*******************************************");
        System.out.println("Sei un nuovo utente o sei già registrato?");
        System.out.println("1. Nuovo utente");
        System.out.println("2. Utente già registrato");
        System.out.print("Inserisci il numero corrispondente: ");
        int sceltaIniziale = scanner.nextInt();
        scanner.nextLine();

        switch (sceltaIniziale) {
            case 1 -> nuovoAccesso();
            case 2 -> accessoRegistrato();
            default -> System.out.println("Scelta non valida. Uscita.");
        }
    }

    private void nuovoAccesso() {
        int tentativiMassimi = 3;
        int tentativiAttuali = 0;

        do {
            System.out.print("Inserisci username fornito: ");
            String providedUsername = scanner.nextLine();
            System.out.print("Inserisci password fornita: ");
            String providedPassword = scanner.nextLine();

            if (configuratore.getAutenticatore().verificaPrimoAccesso(providedUsername, providedPassword)) {
                System.out.println("Accesso riuscito! Benvenuto " + providedUsername);
                richiediRegistrazione();
                return;
            }

            System.out.println("*******************************************");
            System.out.println("* Accesso negato. Credenziali errate.       *");
            System.out.println("*******************************************");
            tentativiAttuali++;
            gestisciTentativi(tentativiMassimi, tentativiAttuali);
        } while (tentativiAttuali < tentativiMassimi);
    }

    private void accessoRegistrato() {
        System.out.print("Inserisci username registrato: ");
        String usernameRegistrato = scanner.nextLine();
        System.out.print("Inserisci password registrata: ");
        String passwordRegistrata = scanner.nextLine();

        if (configuratore.getAutenticatore().verificaAccessoRegistrato(usernameRegistrato, passwordRegistrata)) {
            System.out.println("Accesso riuscito! Benvenuto " + usernameRegistrato);
        } else {
            System.out.println("*******************************************");
            System.out.println("* Accesso negato. Credenziali errate.       *");
            gestisciOpzioniErrore(false);
        }
    }

    private void richiediRegistrazione() {
        System.out.println("*******************************************");
        System.out.println("* È necessario completare la registrazione. *");
        System.out.println("*******************************************");
        boolean registrazioneSuccesso;

        do {
            System.out.print("Inserisci nuovo username: ");
            String nuovoUsername = scanner.nextLine();
            System.out.print("Inserisci nuova password: ");
            String nuovaPassword = scanner.nextLine();
            registrazioneSuccesso = registrazioneCredenziali.registraCredenziali(nuovoUsername, nuovaPassword);

            if (!registrazioneSuccesso) {
                System.out.println("*******************************************");
                System.out.println("* Registrazione fallita. Riprova con credenziali diverse. *");
                System.out.println("*******************************************");
            } else {
                System.out.println("*******************************************");
                System.out.println("* Registrazione completata con successo!    *");
                System.out.println("*******************************************");
            }
        } while (!registrazioneSuccesso);
    }

    private void gestisciOpzioniErrore(boolean isNuovoUtente) {
        int risposta;
        do {
            System.out.println("1. Riprovare");
            System.out.println("2. Tornare indietro");
            System.out.println("3. Uscire definitivamente");
            System.out.print("Inserisci il numero corrispondente: ");
            risposta = scanner.nextInt();
            scanner.nextLine();
            switch (risposta) {
                case 1:
                    if (isNuovoUtente) {
                        nuovoAccesso();
                    } else {
                        accessoRegistrato();
                    }
                    break;
                case 2:
                    avviaMenu();
                    break;
                case 3:
                    System.out.println("Uscita definitiva.");
                    System.exit(0);
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        } while (risposta < 1 || risposta > 3);
    }

    private void gestisciTentativi(int tentativiMassimi, int tentativiAttuali) {
        if (tentativiAttuali >= tentativiMassimi) {
            System.out.println("*******************************************");
            System.out.println("* Numero massimo di tentativi raggiunto.    *");
            System.out.println("* Uscita definitiva.                         *");
            System.exit(0);
        }

        System.out.println("Tentativi rimasti: " + (tentativiMassimi - tentativiAttuali));
        System.out.print("Vuoi riprovare? (1. Sì / 2. No): ");
        int risposta = this.scanner.nextInt();
        this.scanner.nextLine();

        if (risposta == 2) {
            gestisciOpzioniErrore(true);
        } else if (risposta != 1) {
            System.out.println("Scelta non valida. Uscita.");
            System.exit(0);
        }
    }

    public void chiudiScanner() {
        scanner.close();
    }
}