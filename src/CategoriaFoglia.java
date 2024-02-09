import java.awt.*;
import java.util.*;
import java.util.List;

public class CategoriaFoglia extends CategoriaBase {
    public CategoriaFoglia(String nome) {
        super(nome);
    }

    @Override
    public List<CategoriaBase> getSottocategorie() {
        return new ArrayList<>();
    }

    @Override
    public void visualizzaDettagli(String spazi) {
        // Implementa la logica per visualizzare i dettagli della CategoriaFoglia
        System.out.println(spazi + "Categoria foglia: " + getNome());
    }

    @Override
    public String toString() {
        return "CategoriaFoglia{" +
                "nome='" + getNome() + '\'' +
                '}';
    }
}