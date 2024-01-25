public class App {
    public static void main(String[] args) throws Exception {
        Conexao conexao = new Conexao();
        conexao.getConnection();
        System.out.println("Hello, World!");
    }
}
