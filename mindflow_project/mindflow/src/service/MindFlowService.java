package service;

import model.Usuario;
import model.CheckinHumor;
import model.Recurso;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de serviço que implementa a lógica de negócio do MindFlow.
 * Gerencia usuários, check-ins de humor e recomendação de recursos.
 */
public class MindFlowService {
    private List<Usuario> listaUsuarios;
    private List<CheckinHumor> listaCheckins;
    private List<Recurso> listaRecursos;
    private int proximoIdUsuario = 1;
    private int proximoIdCheckin = 1;


    public MindFlowService() {
        this.listaUsuarios = new ArrayList<>();
        this.listaCheckins = new ArrayList<>();
        this.listaRecursos = new ArrayList<>();
        inicializarRecursos();
    }


    private void inicializarRecursos() {
        listaRecursos.add(new Recurso(101, "Técnica de Respiração 4-7-8", "Técnica", 
            "https://mindflow.com/respiracao", "Estresse"));
        listaRecursos.add(new Recurso(102, "Artigo: Gerenciamento de Tempo", "Artigo", 
            "https://mindflow.com/tempo", "Produtividade"));
        listaRecursos.add(new Recurso(103, "Vídeo: Meditação Guiada para Foco", "Vídeo", 
            "https://mindflow.com/meditacao", "Foco"));
        listaRecursos.add(new Recurso(104, "Guia: Trabalho Híbrido Equilibrado", "Artigo", 
            "https://mindflow.com/hibrido", "Bem-estar"));
        listaRecursos.add(new Recurso(105, "Exercício: Alongamento no Trabalho", "Técnica", 
            "https://mindflow.com/alongamento", "Saúde"));
    }


    public boolean cadastrarUsuario(String nome, String email) {
        // Verificar se e-mail já existe
        for (Usuario u : listaUsuarios) {
            if (u.getEmail().equals(email)) {
                return false;
            }
        }
        Usuario novoUsuario = new Usuario(proximoIdUsuario++, nome, email);
        listaUsuarios.add(novoUsuario);
        return true;
    }


    public boolean realizarCheckin(int idUsuario, int nivelHumor, int nivelEstresse) {
        // Verificar se usuário existe
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            return false;
        }

        // Verificar se já existe check-in de hoje
        LocalDate hoje = LocalDate.now();
        for (CheckinHumor c : listaCheckins) {
            if (c.getIdUsuario() == idUsuario && c.getData().equals(hoje)) {
                return false; // Já existe check-in de hoje
            }
        }

        CheckinHumor novoCheckin = new CheckinHumor(proximoIdCheckin++, idUsuario, hoje, nivelHumor, nivelEstresse);
        listaCheckins.add(novoCheckin);
        return true;
    }


    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario u : listaUsuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }


    public String analisarTendencia(int idUsuario) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            return "Usuário não encontrado.";
        }

        List<CheckinHumor> checkinsDoUsuario = new ArrayList<>();
        LocalDate hoje = LocalDate.now();
        LocalDate semanaPassa = hoje.minusDays(7);

        for (CheckinHumor c : listaCheckins) {
            if (c.getIdUsuario() == idUsuario && 
                c.getData().isAfter(semanaPassa) && c.getData().isBefore(hoje.plusDays(1))) {
                checkinsDoUsuario.add(c);
            }
        }

        if (checkinsDoUsuario.isEmpty()) {
            return "Nenhum check-in realizado na última semana.";
        }

        // Calcular média de estresse
        double mediaEstresse = checkinsDoUsuario.stream()
            .mapToInt(CheckinHumor::getNivelEstresse)
            .average()
            .orElse(0);

        double mediaHumor = checkinsDoUsuario.stream()
            .mapToInt(CheckinHumor::getNivelHumor)
            .average()
            .orElse(0);

        StringBuilder analise = new StringBuilder();
        analise.append("=== ANÁLISE DE TENDÊNCIA ===\n");
        analise.append("Usuário: ").append(usuario.getNome()).append("\n");
        analise.append("Período: Últimos 7 dias\n");
        analise.append("Check-ins realizados: ").append(checkinsDoUsuario.size()).append("\n");
        analise.append(String.format("Nível médio de estresse: %.1f/5\n", mediaEstresse));
        analise.append(String.format("Nível médio de humor: %.1f/5\n", mediaHumor));

        // Recomendação baseada na análise
        if (mediaEstresse >= 4) {
            analise.append("\n⚠️  ALERTA: Seu nível de estresse está elevado!\n");
            analise.append("Recomendamos: Técnica de Respiração 4-7-8\n");
        } else if (mediaEstresse >= 3) {
            analise.append("\n📌 ATENÇÃO: Seu nível de estresse está moderado.\n");
            analise.append("Recomendamos: Meditação Guiada para Foco\n");
        } else {
            analise.append("\n✅ Excelente! Seu nível de estresse está sob controle.\n");
            analise.append("Continue assim! 🎉\n");
        }

        return analise.toString();
    }


    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(listaUsuarios);
    }


    public List<Recurso> listarRecursos() {
        return new ArrayList<>(listaRecursos);
    }


    public List<CheckinHumor> listarCheckinsDoUsuario(int idUsuario) {
        List<CheckinHumor> resultado = new ArrayList<>();
        for (CheckinHumor c : listaCheckins) {
            if (c.getIdUsuario() == idUsuario) {
                resultado.add(c);
            }
        }
        return resultado;
    }


    public int getTotalUsuarios() {
        return listaUsuarios.size();
    }


    public int getTotalCheckins() {
        return listaCheckins.size();
    }
}
