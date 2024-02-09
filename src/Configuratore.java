import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Configuratore {
    private final Autenticatore autenticatore;
    private final List<CategoriaBase> categorie;
    private Scanner scanner;

    public Configuratore(List<String> credenzialiRegistrate) {
        this.autenticatore = new Autenticatore(credenzialiRegistrate);
        this.categorie = new ArrayList<>();
    }

    public Autenticatore getAutenticatore() {
        return this.autenticatore;
    }

    public void introduceNuovoComprensorio(){
        MenuComprensorio menuComprensorio = new MenuComprensorio();
        menuComprensorio.creaComprensorioDaConfiguratore();

    }

    public void introduceNuovaGerarchiaCategorie() {
        CategoriaManager categoriaManager = new CategoriaManager(scanner);
        categoriaManager.gestisciCreazioneCategorie();
    }

}
