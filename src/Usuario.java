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
    	byte[] salt = generateSalt();
    	String hashedPassword = hashPassword(usuario.senha, salt);
    	
        bdscript.InserirUsuario(usuario.nome,hashedPassword,salt);
    }
    public int Login(String nome, String senha){
       int id = bdscript.getId(nome, senha);
       if(id==-1){
        limparTerminal.clearScreen();
        System.out.println("Usuario nao encontrado ou senha errada");
        
       }else{
        LimparTerminal.clearScreen();
        System.out.println("Login realizado com sucesso");
       }
       return id;

    }
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    public static String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            return bytesToHex(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
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