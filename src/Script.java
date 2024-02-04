import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Script {

    //Classe para inserir os Lembretes no Banco de dados 
    public void InserirLembrete(String titulo, String descricao, Date data) {
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();

        String sql = "INSERT INTO lembretes (titulo, data, descricao) VALUES (?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, titulo);
            statement.setDate(2, data);
            statement.setString(3, descricao);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Insercao bem-sucedida");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //classe para inserir Usuarios ao Banco de dados
    public void InserirUsuario(String nome, String senha) {
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();

        String sql = "INSERT INTO usuario (nome_usuario, senha) VALUES (?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, senha);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Insercao bem-sucedida");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //classe que compara o nome de usuario e senhas aos presentes no banco de dados afim de retornar o id do usuario para a recuperação dos lembretes
    public int getId(String nome, String senha){
        int id=-1;
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();

        String sql = "SELECT id_usuario FROM trabalho.usuario WHERE nome_usuario = ? AND senha = ?";
        try{
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            id = rs.getInt("id_usuario");
            System.out.println("O ID do usuario é: " + id);
        } else {
            System.out.println("Usuario ou senha incorretos");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao conectar ao banco de dados");
        e.printStackTrace();
    }
        return id;

    }

        

}
