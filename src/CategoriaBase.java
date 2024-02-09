import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class CategoriaBase {
    protected String nome;

    public CategoriaBase(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract List<CategoriaBase> getSottocategorie();

    public abstract void visualizzaDettagli(String spazi);

    @Override
    public String toString() {
        return "CategoriaBase{" +
                "nome='" + nome + '\'' +
                '}';
    }


}