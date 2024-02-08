import javax.xml.crypto.Data;

public class Lembretes {
    Script bdscript = new Script();
    private String titulo, descricao;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setLembrete(Lembretes lembrete,String id_usuario, String nome) {
        bdscript.InserirLembrete(id_usuario, lembrete.titulo, lembrete.descricao, nome);
    }

}