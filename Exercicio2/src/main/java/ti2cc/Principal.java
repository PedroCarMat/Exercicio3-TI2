package ti2cc;

import java.util.List;
import java.util.Scanner;

public class Principal {
    
    private static Scanner scanner = new Scanner(System.in);
    private static FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    public static void main(String[] args) {
        int opcao;
        
        do {
            exibirMenu();
            opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    listarFuncionarios();
                    break;
                case 2:
                    inserirFuncionario();
                    break;
                case 3:
                    atualizarFuncionario();
                    break;
                case 4:
                    excluirFuncionario();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            
            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
            
        } while (opcao != 0);
        
        scanner.close();
    }
    
    private static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE GERENCIAMENTO DE FUNCIONÁRIOS ===");
        System.out.println("1. Listar todos os funcionários");
        System.out.println("2. Inserir novo funcionário");
        System.out.println("3. Atualizar funcionário");
        System.out.println("4. Excluir funcionário");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void listarFuncionarios() {
        System.out.println("\n=== LISTA DE FUNCIONÁRIOS ===");
        List<Funcionario> funcionarios = funcionarioDAO.get();
        
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (Funcionario func : funcionarios) {
                System.out.println(func.toString());
            }
            System.out.println("Total: " + funcionarios.size() + " funcionário(s)");
        }
    }
    
    private static void inserirFuncionario() {
        System.out.println("\n=== INSERIR NOVO FUNCIONÁRIO ===");
        
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            
            System.out.print("Idade: ");
            int idade = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Função: ");
            String funcao = scanner.nextLine();
            
            System.out.print("Sexo (M/F): ");
            char sexo = scanner.nextLine().toUpperCase().charAt(0);
            
            Funcionario funcionario = new Funcionario(id, nome, idade, funcao, sexo);
            
            if (funcionarioDAO.insert(funcionario)) {
                System.out.println("Funcionário inserido com sucesso!");
            } else {
                System.out.println("Erro ao inserir funcionário.");
            }
            
        } catch (Exception e) {
            System.out.println("Erro nos dados informados: " + e.getMessage());
        }
    }
    
    private static void atualizarFuncionario() {
        System.out.println("\n=== ATUALIZAR FUNCIONÁRIO ===");
        
        try {
            System.out.print("ID do funcionário a ser atualizado: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Funcionario funcionario = funcionarioDAO.get(id);
            if (funcionario == null) {
                System.out.println("Funcionário não encontrado!");
                return;
            }
            
            System.out.println("Dados atuais: " + funcionario.toString());
            
            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();
            funcionario.setNome(nome);
            
            System.out.print("Nova idade: ");
            int idade = Integer.parseInt(scanner.nextLine());
            funcionario.setIdade(idade);
            
            System.out.print("Nova função: ");
            String funcao = scanner.nextLine();
            funcionario.setFuncao(funcao);
            
            System.out.print("Novo sexo (M/F): ");
            char sexo = scanner.nextLine().toUpperCase().charAt(0);
            funcionario.setSexo(sexo);
            
            if (funcionarioDAO.update(funcionario)) {
                System.out.println("Funcionário atualizado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar funcionário.");
            }
            
        } catch (Exception e) {
            System.out.println("Erro nos dados informados: " + e.getMessage());
        }
    }
    
    private static void excluirFuncionario() {
        System.out.println("\n=== EXCLUIR FUNCIONÁRIO ===");
        
        try {
            System.out.print("ID do funcionário a ser excluído: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Funcionario funcionario = funcionarioDAO.get(id);
            if (funcionario == null) {
                System.out.println("Funcionário não encontrado!");
                return;
            }
            
            System.out.println("Dados do funcionário: " + funcionario.toString());
            System.out.print("Confirmar exclusão? (S/N): ");
            String confirmacao = scanner.nextLine();
            
            if (confirmacao.equalsIgnoreCase("S")) {
                if (funcionarioDAO.delete(id)) {
                    System.out.println("Funcionário excluído com sucesso!");
                } else {
                    System.out.println("Erro ao excluir funcionário.");
                }
            } else {
                System.out.println("Exclusão cancelada.");
            }
            
        } catch (Exception e) {
            System.out.println("Erro nos dados informados: " + e.getMessage());
        }
    }
}