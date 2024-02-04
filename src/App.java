import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Conexao conexao = new Conexao();
        conexao.getConnection();
        Scanner scanner = new Scanner(System.in);
        int esc=0;
        //menu de Opções
        while(esc!=3){
            System.out.println("Escolha uma opção\n\n");
            System.out.println("1- Criar Usuario");
            System.out.println("2- Login");
            System.out.println("3- Sair");
            System.out.print("Escolha: ");
            esc = scanner.nextInt();

        switch (esc) {
            case 1:
                CriarUsuario();
                break;
            case 2:
                Login();
                break;
            case 3:

                break;
        
            default:
                break;
        }
        }
    }
    //função para criar usuario
    public static void CriarUsuario(){
        String nome="";
        String senha=""; 
        Scanner scanner = new Scanner(System.in);

        while (senha.equals("") && senha.length()<4) {
            System.out.println("Digite seu nome de Usuario");
            nome = scanner.nextLine(); //leitura do nome de usuario

            System.out.println("Digite sua senha");
            senha= scanner.nextLine(); //leitura da senha
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setSenha(senha);
            usuario.setUsuario(usuario); // Cadastra o Usuario dentro do banco de dados
        }
    }
    public static void Login(){
        String nome="";
        String senha=""; 
        int id=-1;
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("Digite seu nome de Usuario");
            nome = scanner.nextLine(); //leitura do nome de usuario

            System.out.println("Digite sua senha");
            senha= scanner.nextLine(); //leitura da senha
            Usuario usuario = new Usuario();
            id=usuario.Login(nome, senha); //passa o nome de usuario e a senha para serem comparados ao banco de dados ser forem compativeis volta o id do usuario ao qual está relacionado aos lembretes que o proprio criou

        }while(id==-1);

    }
}
