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
    
    
    /*
     * try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT salt, hashed_password FROM usuarios WHERE username = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String salt = resultSet.getString("salt");
                        String storedHashedPassword = resultSet.getString("hashed_password");
                        String hashedProvidedPassword = hashPassword(providedPassword, Base64.getDecoder().decode(salt));

                        if (hashedProvidedPassword.equals(storedHashedPassword)) {
                            System.out.println("Senha correta!");
                        } else {
                            System.out.println("Senha incorreta!");
                        }
                    } else {
                        System.out.println("Usuário não encontrado!");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     * 
     * */


}