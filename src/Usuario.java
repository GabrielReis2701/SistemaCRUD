/**
 * Usuario
 */
public class Usuario {
    Script bdscript = new Script();

    private String nome;
    private String senha;

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
        bdscript.InserirUsuario(usuario.nome,usuario.senha);
    }
    public int Login(String nome, String senha){
       int id = bdscript.getId(nome, senha);
       if(id==-1){
        System.out.println("Usuario nao encontrado ou senha errada");
       }else{
        System.out.println("Login realizado com sucesso");
       }
       return id;
    }

}