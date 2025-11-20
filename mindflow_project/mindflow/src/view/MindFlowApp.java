package view;

import service.MindFlowService;
import model.Usuario;
import model.Recurso;
import model.CheckinHumor;
import java.util.List;
import java.util.Scanner;


public class MindFlowApp {
    private MindFlowService service;
    private Scanner scanner;


    public MindFlowApp() {
        this.service = new MindFlowService();
        this.scanner = new Scanner(System.in);
    }


    public static void main(String[] args) {
        MindFlowApp app = new MindFlowApp();
        app.executar();
    }


    public void executar() {
        boolean ativo = true;
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║     BEM-VINDO AO MINDFLOW                              ║");
        System.out.println("║  Plataforma de Bem-Estar Digital para o Trabalho      ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        while (ativo) {
            exibirMenuPrincipal();
            int opcao = lerInteiro("Escolha uma opção: ");
            
            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    realizarCheckin();
                    break;
                case 3:
                    analisarTendencia();
                    break;
                case 4:
                    exibirRecursos();
                    break;
                case 5:
                    exibirEstatisticas();
                    break;
                case 0:
                    System.out.println("\n✨ Obrigado por usar o MindFlow! Cuide-se bem! ✨\n");
                    ativo = false;
                    break;
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.\n");
            }
        }
        scanner.close();
    }


    private void exibirMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║                    MENU PRINCIPAL                      ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║ 1. Cadastrar Novo Usuário                              ║");
        System.out.println("║ 2. Realizar Check-in de Humor e Estresse               ║");
        System.out.println("║ 3. Analisar Tendência de Bem-Estar                     ║");
        System.out.println("║ 4. Visualizar Recursos de Bem-Estar                    ║");
        System.out.println("║ 5. Ver Estatísticas da Plataforma                      ║");
        System.out.println("║ 0. Sair                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }


    private void cadastrarUsuario() {
        System.out.println("\n--- CADASTRO DE NOVO USUÁRIO ---");
        String nome = lerString("Digite o nome completo: ");
        String email = lerString("Digite o e-mail: ");

        if (service.cadastrarUsuario(nome, email)) {
            System.out.println("✅ Usuário cadastrado com sucesso!\n");
        } else {
            System.out.println("❌ Erro: E-mail já cadastrado na plataforma.\n");
        }
    }


    private void realizarCheckin() {
        System.out.println("\n--- REALIZAR CHECK-IN ---");
        
        // Listar usuários
        List<Usuario> usuarios = service.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("❌ Nenhum usuário cadastrado. Cadastre um usuário primeiro.\n");
            return;
        }

        System.out.println("\nUsuários cadastrados:");
        for (Usuario u : usuarios) {
            System.out.println("  ID: " + u.getId() + " | Nome: " + u.getNome());
        }

        int idUsuario = lerInteiro("\nDigite o ID do usuário: ");
        
        if (service.buscarUsuarioPorId(idUsuario) == null) {
            System.out.println("❌ Usuário não encontrado.\n");
            return;
        }

        System.out.println("\nAvalie seu estado atual (escala de 1 a 5):");
        int nivelHumor = lerInteiro("Nível de humor (1=Muito ruim, 5=Excelente): ");
        int nivelEstresse = lerInteiro("Nível de estresse (1=Muito baixo, 5=Muito alto): ");

        if (service.realizarCheckin(idUsuario, nivelHumor, nivelEstresse)) {
            System.out.println("✅ Check-in realizado com sucesso!\n");
        } else {
            System.out.println("❌ Erro: Você já realizou um check-in hoje.\n");
        }
    }


    private void analisarTendencia() {
        System.out.println("\n--- ANÁLISE DE TENDÊNCIA ---");
        
        List<Usuario> usuarios = service.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("❌ Nenhum usuário cadastrado.\n");
            return;
        }

        System.out.println("\nUsuários cadastrados:");
        for (Usuario u : usuarios) {
            System.out.println("  ID: " + u.getId() + " | Nome: " + u.getNome());
        }

        int idUsuario = lerInteiro("\nDigite o ID do usuário para análise: ");
        String analise = service.analisarTendencia(idUsuario);
        System.out.println("\n" + analise);
    }


    private void exibirRecursos() {
        System.out.println("\n--- BIBLIOTECA DE RECURSOS DE BEM-ESTAR ---");
        List<Recurso> recursos = service.listarRecursos();

        if (recursos.isEmpty()) {
            System.out.println("Nenhum recurso disponível.\n");
            return;
        }

        System.out.println("\n📚 Recursos Disponíveis:\n");
        for (Recurso r : recursos) {
            System.out.println("┌─────────────────────────────────────────────────────┐");
            System.out.println("│ ID: " + r.getId());
            System.out.println("│ Título: " + r.getTitulo());
            System.out.println("│ Tipo: " + r.getTipo());
            System.out.println("│ Foco: " + r.getFoco());
            System.out.println("│ Link: " + r.getUrl());
            System.out.println("└─────────────────────────────────────────────────────┘");
        }
    }

    private void exibirEstatisticas() {
        System.out.println("\n--- ESTATÍSTICAS DA PLATAFORMA ---");
        System.out.println("Total de usuários cadastrados: " + service.getTotalUsuarios());
        System.out.println("Total de check-ins realizados: " + service.getTotalCheckins());
        System.out.println("Total de recursos disponíveis: " + service.listarRecursos().size());
        System.out.println();
    }


    private String lerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }


    private int lerInteiro(String prompt) {
        System.out.print(prompt);
        int valor;
        try {
            valor = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            valor = -1;
        }
        return valor;
    }
}
