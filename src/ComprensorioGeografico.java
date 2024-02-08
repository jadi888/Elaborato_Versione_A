import java.util.List;

public class ComprensorioGeografico {
    private String nome;
    private List<String> comuni;

    public ComprensorioGeografico(String nome, List<String> comuni) {
        this.nome = nome;
        this.comuni = comuni;
    }

    public ComprensorioGeografico(){
    }

    public String getNome() {
        return nome;
    }

    public List<String> getComuni() {
        return comuni;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setComuni(List<String> comuni) {
        this.comuni = comuni;
    }
}
