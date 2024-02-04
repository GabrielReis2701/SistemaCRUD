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
    public void setLembrete(Lembretes lembrete,int id_usuario) {
        bdscript.InserirLembrete(id_usuario, lembrete.titulo, lembrete.descricao);
    }

}