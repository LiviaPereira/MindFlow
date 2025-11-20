
const checkinsSimulados = [
    { usuario: 1, data: '2025-11-15', humor: 4, estresse: 2 },
    { usuario: 1, data: '2025-11-16', humor: 3, estresse: 4 },
    { usuario: 1, data: '2025-11-17', humor: 5, estresse: 1 },
    { usuario: 2, data: '2025-11-15', humor: 2, estresse: 5 },
    { usuario: 2, data: '2025-11-16', humor: 3, estresse: 3 },
];


document.getElementById('humor')?.addEventListener('input', function() {
    const valor = parseInt(this.value);
    const emoji = document.getElementById('humorEmoji');
    const texto = document.getElementById('humorTexto');
    
    const emojis = {
        1: { emoji: '😢', texto: 'Muito Ruim' },
        2: { emoji: '😞', texto: 'Ruim' },
        3: { emoji: '😐', texto: 'Neutro' },
        4: { emoji: '🙂', texto: 'Bom' },
        5: { emoji: '😄', texto: 'Excelente' }
    };
    
    emoji.textContent = emojis[valor].emoji;
    texto.textContent = emojis[valor].texto;
});


document.getElementById('estresse')?.addEventListener('input', function() {
    const valor = parseInt(this.value);
    const emoji = document.getElementById('estresseEmoji');
    const texto = document.getElementById('estresseTexto');
    
    const emojis = {
        1: { emoji: '😌', texto: 'Muito Baixo' },
        2: { emoji: '😊', texto: 'Baixo' },
        3: { emoji: '😐', texto: 'Moderado' },
        4: { emoji: '😰', texto: 'Alto' },
        5: { emoji: '😱', texto: 'Muito Alto' }
    };
    
    emoji.textContent = emojis[valor].emoji;
    texto.textContent = emojis[valor].texto;
});


document.getElementById('checkinForm')?.addEventListener('submit', function(e) {
    e.preventDefault();
    
    const usuario = document.getElementById('usuario').value;
    const humor = document.getElementById('humor').value;
    const estresse = document.getElementById('estresse').value;
    const feedback = document.getElementById('feedback');
    
    if (!usuario) {
        mostrarFeedback('Por favor, selecione um usuário.', 'error');
        return;
    }
    

    const hoje = new Date().toISOString().split('T')[0];
    

    const checkinExistente = checkinsSimulados.some(c => 
        c.usuario === parseInt(usuario) && c.data === hoje
    );
    
    if (checkinExistente) {
        mostrarFeedback('Você já realizou um check-in hoje. Volte amanhã!', 'error');
        return;
    }
    

    checkinsSimulados.push({
        usuario: parseInt(usuario),
        data: hoje,
        humor: parseInt(humor),
        estresse: parseInt(estresse)
    });
    

    let recomendacao = '';
    const nivelEstresse = parseInt(estresse);
    
    if (nivelEstresse >= 4) {
        recomendacao = ' Recomendamos: Técnica de Respiração 4-7-8 para reduzir o estresse.';
    } else if (nivelEstresse >= 3) {
        recomendacao = ' Recomendamos: Meditação Guiada para Foco para equilibrar seu dia.';
    } else {
        recomendacao = ' Excelente! Continue assim! 🎉';
    }
    
    mostrarFeedback('✅ Check-in realizado com sucesso!' + recomendacao, 'success');
    

    this.reset();
    document.getElementById('humorEmoji').textContent = '😐';
    document.getElementById('humorTexto').textContent = 'Neutro';
    document.getElementById('estresseEmoji').textContent = '😐';
    document.getElementById('estresseTexto').textContent = 'Moderado';
});


function mostrarFeedback(mensagem, tipo) {
    const feedback = document.getElementById('feedback');
    if (feedback) {
        feedback.textContent = mensagem;
        feedback.className = 'feedback ' + tipo;
        
        // Auto-esconder após 5 segundos
        setTimeout(() => {
            feedback.className = 'feedback';
        }, 5000);
    }
}


function analisarTendenciaUsuario(idUsuario) {
    const checkinsDoUsuario = checkinsSimulados.filter(c => c.usuario === idUsuario);
    
    if (checkinsDoUsuario.length === 0) {
        return 'Nenhum check-in realizado ainda.';
    }
    
    const mediaEstresse = (checkinsDoUsuario.reduce((sum, c) => sum + c.estresse, 0) / checkinsDoUsuario.length).toFixed(1);
    const mediaHumor = (checkinsDoUsuario.reduce((sum, c) => sum + c.humor, 0) / checkinsDoUsuario.length).toFixed(1);
    
    let analise = `Análise de Tendência:\n`;
    analise += `Check-ins realizados: ${checkinsDoUsuario.length}\n`;
    analise += `Nível médio de estresse: ${mediaEstresse}/5\n`;
    analise += `Nível médio de humor: ${mediaHumor}/5\n`;
    
    if (mediaEstresse >= 4) {
        analise += `\n⚠️ Seu nível de estresse está elevado. Recomendamos técnicas de relaxamento.`;
    } else if (mediaEstresse >= 3) {
        analise += `\n📌 Seu nível de estresse está moderado. Continue monitorando.`;
    } else {
        analise += `\n✅ Seu nível de estresse está sob controle!`;
    }
    
    return analise;
}


function formatarData(data) {
    const opcoes = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(data).toLocaleDateString('pt-BR', opcoes);
}


function inicializarTooltips() {
    const elementos = document.querySelectorAll('[data-tooltip]');
    elementos.forEach(el => {
        el.addEventListener('mouseenter', function() {
            const tooltip = document.createElement('div');
            tooltip.className = 'tooltip';
            tooltip.textContent = this.dataset.tooltip;
            this.appendChild(tooltip);
        });
        
        el.addEventListener('mouseleave', function() {
            const tooltip = this.querySelector('.tooltip');
            if (tooltip) tooltip.remove();
        });
    });
}


function validarEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}


function observarElementos() {
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.opacity = '1';
                entry.target.style.transform = 'translateY(0)';
            }
        });
    }, { threshold: 0.1 });
    
    document.querySelectorAll('.info-card, .recurso-card').forEach(el => {
        el.style.opacity = '0';
        el.style.transform = 'translateY(20px)';
        el.style.transition = 'opacity 0.5s ease, transform 0.5s ease';
        observer.observe(el);
    });
}


document.addEventListener('DOMContentLoaded', function() {
    inicializarTooltips();
    observarElementos();
    

    const humorSlider = document.getElementById('humor');
    const estresseSlider = document.getElementById('estresse');
    
    if (humorSlider) {
        humorSlider.dispatchEvent(new Event('input'));
    }
    
    if (estresseSlider) {
        estresseSlider.dispatchEvent(new Event('input'));
    }
});


function exportarDados() {
    const dados = JSON.stringify(checkinsSimulados, null, 2);
    const blob = new Blob([dados], { type: 'application/json' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'mindflow_checkins.json';
    a.click();
}

function limparDados() {
    if (confirm('Tem certeza que deseja limpar todos os dados?')) {
        checkinsSimulados.length = 0;
        localStorage.clear();
        alert('Dados limpos com sucesso!');
    }
}

function gerarRelatorio() {
    let relatorio = 'RELATÓRIO DE BEM-ESTAR - MINDFLOW\n';
    relatorio += '================================\n\n';
    
    const usuarios = [...new Set(checkinsSimulados.map(c => c.usuario))];
    
    usuarios.forEach(usuarioId => {
        const checkinsUsuario = checkinsSimulados.filter(c => c.usuario === usuarioId);
        const mediaEstresse = (checkinsUsuario.reduce((sum, c) => sum + c.estresse, 0) / checkinsUsuario.length).toFixed(1);
        const mediaHumor = (checkinsUsuario.reduce((sum, c) => sum + c.humor, 0) / checkinsUsuario.length).toFixed(1);
        
        relatorio += `Usuário ID: ${usuarioId}\n`;
        relatorio += `Check-ins: ${checkinsUsuario.length}\n`;
        relatorio += `Estresse médio: ${mediaEstresse}/5\n`;
        relatorio += `Humor médio: ${mediaHumor}/5\n`;
        relatorio += `---\n`;
    });
    
    console.log(relatorio);
    return relatorio;
}


window.mindflow = {
    analisarTendencia: analisarTendenciaUsuario,
    exportarDados: exportarDados,
    limparDados: limparDados,
    gerarRelatorio: gerarRelatorio
};
