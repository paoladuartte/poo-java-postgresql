package projBancodeDadosPostgre;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private static final Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {

        int op;

        do {
            op = menu();

            try {
                if (op == 1) {
                    System.out.println("Programa Adicionando Dados");
                    adicionaDados();

                } else if (op == 2) {
                    System.out.println("Programa Alterando dados");
                    alteraDados();

                } else if (op == 3) {
                    System.out.println("Programa Excluindo dados");
                    excluiDados();

                } else if (op == 4) {
                    System.out.println("Listando dados!");
                    exibeDados();

                } else if (op == 5) {
                    System.out.println("Programa finalizado");

                } else {
                    System.out.println("Opção inválida!");
                }

            } catch (SQLException e) {
                System.out.println("Erro ao acessar o banco de dados:");
                e.printStackTrace();
            }

        } while (op != 5);
    }

    public static int menu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1 - Incluir dados");
        System.out.println("2 - Alterar dados");
        System.out.println("3 - Excluir dados");
        System.out.println("4 - Listar dados");
        System.out.println("5 - Finalizar programa");
        System.out.println("\nEscolha sua opção: ");

        try {
            int opc = Integer.parseInt(entrada.nextLine());
            return opc;
        } catch (NumberFormatException e) {
            System.out.println("Digite uma opção numérica válida.");
            return 0;
        }
    }

    // Listar
    public static void exibeDados() throws SQLException {

        PessoaDAO dao = new PessoaDAO();
        List<Pessoa> pessoas = dao.getLista();

        if (pessoas.isEmpty()) {
            System.out.println("Não existem pessoas cadastradas.");
            return;
        }

        for (Pessoa pessoa : pessoas) {
            System.out.println("-----------------------");
            System.out.println("Id: " + pessoa.getIdPes());
            System.out.println("Nome: " + pessoa.getNomePes());
            System.out.println("Data de Nascimento: " + pessoa.getDataNascPes());
            System.out.println("Rua: " + pessoa.getRuaPes());
            System.out.println("Número: " + pessoa.getNumeroPes());
            System.out.println("Cidade: " + pessoa.getCidadePes());
        }
    }

    // Adicionar
    public static void adicionaDados() throws SQLException {

        PessoaDAO dao = new PessoaDAO();
        Pessoa p = new Pessoa();

        try {
       
            System.out.print("Nome: ");
            p.setNomePes(entrada.nextLine());

            System.out.print("Data de Nascimento (AAAA-MM-DD): ");
            p.setDataNascPes(entrada.nextLine());

            System.out.print("Rua: ");
            p.setRuaPes(entrada.nextLine());

            System.out.print("Número: ");
            p.setNumeroPes(Integer.parseInt(entrada.nextLine()));

            System.out.print("Cidade: ");
            p.setCidadePes(entrada.nextLine());

            dao.adicionar(p);

        } catch (InputMismatchException e) {
            System.out.println("Erro de digitação.");
        } catch (NumberFormatException e) {
            System.out.println("O número deve ser um valor inteiro.");
        } catch (IllegalArgumentException e) {
            System.out.println("Data inválida. Use o formato AAAA-MM-DD.");
        }
    }

    // Excluir
    public static void excluiDados() throws SQLException {

        PessoaDAO dao = new PessoaDAO();
        Pessoa p = new Pessoa();

        try {
            System.out.print("Digite o ID da pessoa: ");
            p.setIdPes(Integer.parseInt(entrada.nextLine()));

            dao.apagar(p);

        } catch (NumberFormatException e) {
            System.out.println("Digite um ID numérico válido.");
        }
    }

    // Alterar
    public static void alteraDados() throws SQLException {

        PessoaDAO dao = new PessoaDAO();
        Pessoa p = new Pessoa();

        try {
            System.out.print("Digite o ID da pessoa: ");
            p.setIdPes(Integer.parseInt(entrada.nextLine()));

            System.out.print("Deseja alterar o nome? (S/N): ");
            if (entrada.nextLine().equalsIgnoreCase("S")) {
                System.out.print("Digite o nome: ");
                p.setNomePes(entrada.nextLine());
            }

            System.out.print(
                    "Deseja alterar a data de nascimento? (S/N): ");
            if (entrada.nextLine().equalsIgnoreCase("S")) {
                System.out.print("Digite a data (AAAA-MM-DD): ");
                p.setDataNascPes(entrada.nextLine());
            }

            System.out.print("Deseja alterar a cidade? (S/N): ");
            if (entrada.nextLine().equalsIgnoreCase("S")) {
                System.out.print("Digite a cidade: ");
                p.setCidadePes(entrada.nextLine());
            }

            System.out.print("Deseja alterar a rua? (S/N): ");
            if (entrada.nextLine().equalsIgnoreCase("S")) {
                System.out.print("Digite a rua: ");
                p.setRuaPes(entrada.nextLine());
            }

            System.out.print("Deseja alterar o número? (S/N): ");
            if (entrada.nextLine().equalsIgnoreCase("S")) {
                System.out.print("Digite o número: ");
                p.setNumeroPes(
                        Integer.parseInt(entrada.nextLine()));
            }

            dao.alterar(p);

        } catch (NumberFormatException e) {
            System.out.println(
                    "ID e número devem ser valores inteiros.");
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Data inválida. Use o formato AAAA-MM-DD.");
        }
    }
}
