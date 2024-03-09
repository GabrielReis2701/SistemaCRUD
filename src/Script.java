import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Script {

    // Classe para inserir os Lembretes no Banco de dados
    public void InserirLembrete(String id_usuario, String titulo, String descricao, String nome) {
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();

        String sql = "INSERT INTO lembrete (id_usuario, titulo, data, descricao, nome) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id_usuario);
            statement.setString(2, titulo);
            statement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            statement.setString(4, descricao);
            statement.setString(5, nome);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Insercao bem-sucedida");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // classe para inserir Usuarios ao Banco de dados
    public void InserirUsuario(String nome, String hash_senha, byte[] salt) {
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();

        String sql = "INSERT INTO usuario (nome_usuario, senha, salt) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, hash_senha);
            statement.setString(3, Base64.getEncoder().encodeToString(salt));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Insercao bem-sucedida");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // função que exibe os lembretes antes da edição
    public int[] ExibirLembretes(String id_usuario, String nome) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        String id = "";
        String titulo = "", descricao = "";
        String salt = getSalt(nome);
        Date data;
        int i = 0;
        int[] id_lem = new int[100];
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();

        String sql = "SELECT * FROM trabalho.lembrete WHERE id_usuario = ? ORDER BY id_lembrete";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id_usuario);
            ResultSet rs = statement.executeQuery();
            System.out.println(
                    "--------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-10s | %-10s | %-20s | %-50s |\n", "Usuario", "Nº Lembrete", "Data", "Titulo",
                    "Descrição");
            System.out.println(
                    "--------------------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                id = rs.getString("nome");
                id_lem[i + 1] = rs.getInt("id_lembrete");
                titulo = rs.getString("titulo");
                titulo = CriptografarMensagem.Descriptografar(salt, titulo);
                descricao = rs.getString("descricao");
                descricao = CriptografarMensagem.Descriptografar(salt, descricao);
                data = rs.getDate("data");
                i++;
                System.out.printf("| %-10s | %-10d | %-10s | %-20s | %-50s |\n", id, i, data.toString(), titulo,
                        descricao);

            }
            System.out.println(
                    "--------------------------------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados");
            e.printStackTrace();
        }
        return id_lem;
    }

    // função para editar os Lembretes
    public void EditarLembrete(int id_lembrete, Lembretes lembrete) {
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();

        String sql = "UPDATE lembrete SET titulo = ?, descricao = ?, data = ? WHERE id_lembrete = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, lembrete.getTitulo());
            statement.setString(2, lembrete.getDescricao());
            statement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            statement.setInt(4, id_lembrete);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {

                System.out.println("A atualização foi bem-sucedida!");
            } else {
                System.out.println("Numero do Lembrete invalido");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados");
            e.printStackTrace();
        }

    }

    public void RemoverLembrete(int id_lembrete) {
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();

        String sql = "DELETE FROM trabalho.lembrete WHERE id_lembrete = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id_lembrete);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Lembrete Removido!");
            } else {
                System.out.println("Numero do Lembrete invalido");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados");
            e.printStackTrace();
        }

    }

    public String Login(String nome, String senha) {
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();
        String hashedProvidedPassword = "";

        String sql = "SELECT salt, senha FROM trabalho.usuario WHERE nome_usuario = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String salt = resultSet.getString("salt");
                    String storedHashedPassword = resultSet.getString("senha");
                    hashedProvidedPassword = HashPassword.hashPassword(senha, Base64.getDecoder().decode(salt));

                    if (hashedProvidedPassword.equals(storedHashedPassword)) {
                        System.out.println("Login realizado com sucesso!!!");
                    } else {
                        System.out.println("Senha ou nome de usuario incorretos!");
                        hashedProvidedPassword = "";

                    }
                } else {
                    System.out.println("Usuário não encontrado!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hashedProvidedPassword;
    }

    public String getSalt(String nome) {
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();
        String salt = "";
        String sql = "SELECT salt FROM trabalho.usuario WHERE nome_usuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    salt = resultSet.getString("salt");

                } else {
                    System.out.println("Salt não encontrado!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return salt;
    }

}
