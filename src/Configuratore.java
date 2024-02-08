import java.util.List;

public class Configuratore {
    private final Autenticatore autenticatore;

    public Configuratore(List<String> credenzialiRegistrate) {
        this.autenticatore = new Autenticatore(credenzialiRegistrate);
    }

    public Autenticatore getAutenticatore() {
        return this.autenticatore;
    }

    public void introduceNuovoComprensorio(){
        MenuComprensorio menuComprensorio = new MenuComprensorio();
        menuComprensorio.creaComprensorioDaConfiguratore();


    }
}
