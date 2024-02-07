import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Conexao conexao = new Conexao();
        conexao.getConnection();
        Scanner scanner = new Scanner(System.in);
        int esc = 0;
        // menu de Opções
        while (esc != 3) {
            System.out.println(
                    "----------------------------------------------------------------------------------------");
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
                default:
                    break;
            }
        }
        LimparTerminal.clearScreen();
    }

    // função para criar usuario
    public static void CriarUsuario() {
        String nome = "";
        String senha = "";
        Scanner scanner = new Scanner(System.in);

        while (senha.equals("") && senha.length() < 4) {
            System.out.println("Digite seu nome de Usuario");
            nome = scanner.nextLine(); // leitura do nome de usuario

            System.out.println("Digite sua senha");
            senha = scanner.nextLine(); // leitura da senha
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setSenha(senha);
            usuario.setUsuario(usuario); // Cadastra o Usuario dentro do banco de dados
        }
    }

    //funçao de login
    public static void Login() {
        String nome = "";
        String senha = "";
        int id = -1;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Digite seu nome de Usuario");
            nome = scanner.nextLine(); // leitura do nome de usuario

            System.out.println("Digite sua senha");
            senha = scanner.nextLine(); // leitura da senha
            Usuario usuario = new Usuario();
            id = usuario.Login(nome, senha); // passa o nome de usuario e a senha para serem comparados ao banco de
                                             // dados ser forem compativeis volta o id do usuario ao qual está
                                             // relacionado aos lembretes que o proprio criou

        } while (id == -1);
        LembretesOpc(id);

    }

    // função para criar editar ou remover um lembrete
    public static void LembretesOpc(int id_usuario) {
        Scanner scanner = new Scanner(System.in);
        int esc = 0;
        // menu de Opções
        while (esc != 5) {
            System.out.println("Escolha uma opção\n\n");
            System.out.println("1- Criar Um Lembrete");
            System.out.println("2- Ver Lembretes");
            System.out.println("3- Editar um Lembrete");
            System.out.println("4- Remover Um lembrete");
            System.out.println("5- Deslogar");
            System.out.print("Escolha: ");
            esc = scanner.nextInt();

            switch (esc) {
                case 1:
                    CriarLembrete(id_usuario);

                    break;
                case 2:
                    Lembretes lembretes = new Lembretes();
                    lembretes.bdscript.ExibirLembretes(id_usuario);
                    break;
                case 3:
                    LimparTerminal.clearScreen();
                    EditarLembrete(id_usuario);
                    break;
                case 4:
                	RemoverLembrete(id_usuario);
                    break;

                default:
                    break;
            }
        }
        LimparTerminal.clearScreen();

    }

    private static void CriarLembrete(int id_usuario) {
        Scanner scanner = new Scanner(System.in);
        String titulo = "", descricao = "";
        Lembretes lembretes = new Lembretes();
        System.out.print("informe o titulo do Lembrete: ");
        titulo = scanner.nextLine();
        System.out.print("\nDigite a descricao do Lembrete: ");
        descricao = scanner.nextLine();
        lembretes.setTitulo(titulo);
        lembretes.setDescricao(descricao);
        lembretes.setLembrete(lembretes, id_usuario);
    }

    private static void EditarLembrete(int id_usuario) {
        Scanner scanner = new Scanner(System.in);
        int[] lista = new int[10];
        int numero = -1;
        String titulo = "", descricao = "";
        Lembretes lembretes = new Lembretes();
        lista = lembretes.bdscript.ExibirLembretes(id_usuario);
        System.out.print("Informe o numero do lembrete que deseja editar: ");
        numero = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Informe o novo titulo: ");
        titulo = scanner.nextLine();

        System.out.print("\nInforme o novo lembrete: ");
        descricao = scanner.nextLine();

        lembretes.setTitulo(titulo);
        lembretes.setDescricao(descricao);
        lembretes.bdscript.EditarLembrete(lista[numero], lembretes);
        
    }

    private static void RemoverLembrete(int id_usuario) {
    	Scanner scanner = new Scanner(System.in);
    	int[] lista = new int[10];
        int numero = -1;
        String titulo = "", descricao = "";
        Lembretes lembretes = new Lembretes();
        lista = lembretes.bdscript.ExibirLembretes(id_usuario);
        System.out.print("Informe o numero do lembrete que deseja remover: ");
        numero = scanner.nextInt();
        scanner.nextLine();
        lembretes.bdscript.RemoverLembrete(lista[numero]);
        
        
    }
}
