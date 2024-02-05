import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Script {

    //Classe para inserir os Lembretes no Banco de dados 
    public void InserirLembrete(int id_usuario, String titulo, String descricao) {
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();

        String sql = "INSERT INTO lembrete (id_usuario, titulo, data, descricao) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id_usuario);
            statement.setString(2, titulo);
            statement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            statement.setString(4, descricao);

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
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, senha);
            ResultSet rs = statement.executeQuery();

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
    //função que exibe os lembretes antes da edição
    public int[] ExibirLembretes(int id_usuario){
        int id=-1;
        String titulo="",descricao="";
        Date data;
        int i=0;
        int[] id_lem = new int[100];
        Conexao conexao = new Conexao();
        Connection connection = conexao.getConnection();

        String sql = "SELECT * FROM trabalho.lembrete WHERE id_usuario = ? ORDER BY id_lembrete";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id_usuario);
            ResultSet rs = statement.executeQuery();
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-10s | %-10s | %-20s | %-50s |\n", "Nº usuario", "Nº Lembrete", "Data", "Titulo", "Descrição");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                id = rs.getInt("id_usuario");
                id_lem[i+1] = rs.getInt("id_lembrete");
                titulo = rs.getString("titulo");
                descricao = rs.getString("descricao");
                data = rs.getDate("data");
                i++;
                System.out.printf("| %-10d | %-10d | %-10s | %-20s | %-50s |\n", id, i, data.toString(), titulo, descricao);
                
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");

        }catch (SQLException e) {
        System.out.println("Erro ao conectar ao banco de dados");
        e.printStackTrace();
    }
        return id_lem;
    }
    //função para editar os Lembretes
    public void EditarLembrete(int id_lembrete, Lembretes lembrete){
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
                LimparTerminal.clearScreen();
                System.out.println("A atualização foi bem-sucedida!");
            }else{
                LimparTerminal.clearScreen();
                System.out.println("Numero do Lembrete invalido"); 
            }
        }catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados");
            e.printStackTrace();
        }

    }

        

}
