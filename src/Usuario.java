/**
 * Usuario
 */
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
public class Usuario {
    Script bdscript = new Script();
    LimparTerminal limparTerminal;
    HashPassword hashPassword;

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
    	byte[] salt = HashPassword.generateSalt();
    	String hashedPassword = HashPassword.hashPassword(usuario.senha, salt);
    	
        bdscript.InserirUsuario(usuario.nome,hashedPassword,salt);
    }
    
    public String Login(String nome, String senha){
       String salt = bdscript.Login(nome, senha);
       if(salt.equals("")){
        limparTerminal.clearScreen();
        System.out.println("Usuario nao encontrado ou senha errada");
        
       }else{
        LimparTerminal.clearScreen();
        System.out.println("Login realizado com sucesso");
       }
       return salt;

    }

}