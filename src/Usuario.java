/**
 * Usuario
 */
public class Usuario {

    private String nome;
    private String senha;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setUsuario(Usuario usuario) {
        this.nome = usuario.nome;
        this.senha = usuario.senha;
    }

}