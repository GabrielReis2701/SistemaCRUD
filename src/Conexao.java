import java.sql.*;

//classe que faz a conecção com o banco de dados
public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/trabalho";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("conectado");
            return connection;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
