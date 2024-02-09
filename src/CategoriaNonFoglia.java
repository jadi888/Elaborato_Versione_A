import java.awt.*;
import java.util.*;
import java.util.List;

public class CategoriaNonFoglia extends CategoriaBase {
    private Campo campo;
    private List<CategoriaBase> sottocategorie;

    public CategoriaNonFoglia(String nome, Campo campo, List<CategoriaBase> sottocategorie) {
        super(nome);
        this.campo = campo;
        this.sottocategorie = sottocategorie;
    }

    public CategoriaNonFoglia(String nome, Campo campo) {
        super(nome);
        this.campo = campo;
        this.sottocategorie = List.of();
    }

    public void aggiungiSottocategoria(CategoriaBase sottocategoria) {
        sottocategorie.add(sottocategoria);
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    @Override
    public List<CategoriaBase> getSottocategorie() {
        return sottocategorie;
    }

    public Campo getCampo(){
        return campo;
    }

    @Override
    public void visualizzaDettagli(String spazi) {
        // Implementa la logica per visualizzare i dettagli della CategoriaNonFoglia
        System.out.println(spazi + "Categoria non foglia: " + getNome());
        System.out.println(spazi + "Campo: " + campo.getNome() + " (" + String.join(", ", campo.getDominio()) + ")");
    }

    @Override
    public String toString() {
        return "CategoriaNonFoglia{" +
                "nome='" + getNome() + '\'' +
                ", campo=" + campo +
                ", sottocategorie=" + sottocategorie +
                '}';
    }
}