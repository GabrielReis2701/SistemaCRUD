import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Script {

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
                System.out.println("Inserção bem-sucedida!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
