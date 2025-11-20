# MindFlow - Plataforma de Bem-Estar Digital

## 📋 Descrição

MindFlow é uma plataforma inovadora desenvolvida para o **Desafio FIAP 2025** com foco no futuro do trabalho. A solução aborda a importância da **saúde mental e bem-estar** dos colaboradores em ambientes de trabalho híbrido, oferecendo ferramentas para monitoramento, análise de tendências e acesso a recursos personalizados.

## 🎯 Objetivo

Criar uma solução digital que permita aos colaboradores:
- Monitorar seu humor e nível de estresse diariamente
- Receber análises de tendência de bem-estar
- Acessar uma biblioteca de recursos (artigos, vídeos, técnicas)
- Identificar padrões de estresse e receber recomendações personalizadas

## 🏗️ Arquitetura

O projeto é dividido em três camadas:

### 1. **Model (Entidades)**
- `Usuario.java` - Representa um usuário da plataforma
- `CheckinHumor.java` - Registra humor e estresse do usuário
- `Recurso.java` - Representa recursos de bem-estar

### 2. **Service (Lógica de Negócio)**
- `MindFlowService.java` - Gerencia usuários, check-ins e análises

### 3. **View (Interface)**
- `MindFlowApp.java` - Menu interativo em console

## 📁 Estrutura de Pastas

```
mindflow/
├── src/
│   ├── model/
│   │   ├── Usuario.java
│   │   ├── CheckinHumor.java
│   │   └── Recurso.java
│   ├── service/
│   │   └── MindFlowService.java
│   └── view/
│       └── MindFlowApp.java
├── bin/
│   └── (arquivos compilados)
├── web/
│   ├── index.html
│   ├── recursos.html
│   ├── estilo.css
│   └── script.js
├── db/
│   └── mindflow_db.sql
├── diagrama_uml.mmd
├── diagrama_der.mmd
└── README.md
```

## 🚀 Como Compilar e Executar

### Compilação
```bash
cd mindflow
mkdir -p bin
javac -d bin src/model/*.java src/service/*.java src/view/*.java
```

### Execução
```bash
java -cp bin view.MindFlowApp
```

## 🌐 Interface Web

A plataforma web está disponível em:
- **index.html** - Página principal para realizar check-in
- **recursos.html** - Biblioteca de recursos de bem-estar
- **estilo.css** - Estilos responsivos
- **script.js** - Lógica interativa

Para visualizar a interface web, abra `index.html` em um navegador.

## 📊 Banco de Dados

O script SQL (`mindflow_db.sql`) contém:
- **3 tabelas principais:**
  - `USUARIO` - Dados dos colaboradores
  - `CHECKIN_HUMOR` - Registros de humor e estresse
  - `RECURSO_BEM_ESTAR` - Biblioteca de recursos

- **Dados de exemplo** para 5 usuários com múltiplos check-ins

## 📈 Funcionalidades Principais

### 1. Cadastro de Usuários
- Adicionar novos colaboradores à plataforma
- Validação de e-mail único

### 2. Check-in Diário
- Registrar humor (escala 1-5)
- Registrar nível de estresse (escala 1-5)
- Um check-in por dia por usuário

### 3. Análise de Tendência
- Análise dos últimos 7 dias
- Cálculo de média de estresse e humor
- Recomendações personalizadas baseadas no nível de estresse

### 4. Biblioteca de Recursos
- 5 recursos iniciais (artigos, vídeos, técnicas)
- Filtro por área de foco
- Interface responsiva

### 5. Estatísticas
- Total de usuários cadastrados
- Total de check-ins realizados
- Total de recursos disponíveis

## 🎨 Design e UX

A interface web foi desenvolvida com:
- **Design responsivo** para desktop, tablet e mobile
- **Paleta de cores** moderna e acessível
- **Emojis interativos** para melhor experiência do usuário
- **Sliders visuais** para seleção de humor e estresse

## 📚 Diagramas

- **diagrama_uml.png** - Diagrama de classes UML
- **diagrama_der.png** - Diagrama de Entidade-Relacionamento

## 🔐 Segurança

- Validação de entrada em todos os formulários
- Proteção contra duplicação de e-mail
- Restrições de nível de estresse/humor (1-5)

## 🌱 Sustentabilidade e Impacto Social

O MindFlow alinha-se com os **Objetivos de Desenvolvimento Sustentável (ODS):**
- **ODS 3** - Saúde e bem-estar
- **ODS 4** - Educação de qualidade
- **ODS 8** - Trabalho decente e crescimento econômico
- **ODS 10** - Redução das desigualdades

## 🚀 Próximas Melhorias

- Integração com banco de dados real
- Autenticação de usuários
- Análises mais avançadas com gráficos
- Notificações push para recomendações
- Integração com IA para análises preditivas
- Aplicativo mobile

## 👥 Equipe

- Lívia Pereira Dias Correa - RM 559414

## 📄 Licença

Este projeto é fornecido como material educacional.

---

**Desenvolvido com ❤️ para o futuro do trabalho**
