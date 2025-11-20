-- ============================================================
-- SCRIPT SQL - PROJETO MINDFLOW
-- Plataforma de Bem-Estar Digital para o Trabalho Híbrido
-- ============================================================

-- 1. CRIAÇÃO DA TABELA USUARIO
-- Armazena informações básicas dos colaboradores cadastrados
CREATE TABLE USUARIO (
    id_usuario INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    data_cadastro DATE NOT NULL
);

-- 2. CRIAÇÃO DA TABELA RECURSO_BEM_ESTAR
-- Armazena os recursos disponíveis na biblioteca (artigos, vídeos, técnicas)
CREATE TABLE RECURSO_BEM_ESTAR (
    id_recurso INT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    url VARCHAR(255),
    foco VARCHAR(50) NOT NULL
);

-- 3. CRIAÇÃO DA TABELA CHECKIN_HUMOR
-- Armazena os registros de check-in de humor e estresse dos usuários
CREATE TABLE CHECKIN_HUMOR (
    id_checkin INT PRIMARY KEY,
    id_usuario INT NOT NULL,
    data_checkin DATE NOT NULL,
    nivel_humor INT NOT NULL CHECK (nivel_humor BETWEEN 1 AND 5),
    nivel_estresse INT NOT NULL CHECK (nivel_estresse BETWEEN 1 AND 5),
    
    -- Restrição para garantir que um usuário só faça um check-in por dia
    UNIQUE (id_usuario, data_checkin),
    
    -- Chave estrangeira referenciando a tabela USUARIO
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
);

-- ============================================================
-- INSERÇÃO DE DADOS DE EXEMPLO (DML)
-- ============================================================

-- Inserir Usuários
INSERT INTO USUARIO (id_usuario, nome, email, data_cadastro) VALUES
(1, 'Ana Silva', 'ana.silva@mindflow.com', '2025-11-01'),
(2, 'Bruno Costa', 'bruno.costa@mindflow.com', '2025-11-05'),
(3, 'Carla Mendes', 'carla.mendes@mindflow.com', '2025-11-08'),
(4, 'Diego Santos', 'diego.santos@mindflow.com', '2025-11-10'),
(5, 'Elisa Oliveira', 'elisa.oliveira@mindflow.com', '2025-11-12');

-- Inserir Recursos de Bem-Estar
INSERT INTO RECURSO_BEM_ESTAR (id_recurso, titulo, tipo, url, foco) VALUES
(101, 'Técnica de Respiração 4-7-8', 'Técnica', 'https://mindflow.com/respiracao', 'Estresse'),
(102, 'Artigo: Gerenciamento de Tempo', 'Artigo', 'https://mindflow.com/tempo', 'Produtividade'),
(103, 'Vídeo: Meditação Guiada para Foco', 'Vídeo', 'https://mindflow.com/meditacao', 'Foco'),
(104, 'Guia: Trabalho Híbrido Equilibrado', 'Artigo', 'https://mindflow.com/hibrido', 'Bem-estar'),
(105, 'Exercício: Alongamento no Trabalho', 'Técnica', 'https://mindflow.com/alongamento', 'Saúde');

-- Inserir Check-ins de Humor (Usuário 1 - Ana Silva)
INSERT INTO CHECKIN_HUMOR (id_checkin, id_usuario, data_checkin, nivel_humor, nivel_estresse) VALUES
(1, 1, '2025-11-15', 4, 2),
(2, 1, '2025-11-16', 3, 4),
(3, 1, '2025-11-17', 5, 1),
(4, 1, '2025-11-18', 4, 3);

-- Inserir Check-ins de Humor (Usuário 2 - Bruno Costa)
INSERT INTO CHECKIN_HUMOR (id_checkin, id_usuario, data_checkin, nivel_humor, nivel_estresse) VALUES
(5, 2, '2025-11-15', 2, 5),
(6, 2, '2025-11-16', 3, 3),
(7, 2, '2025-11-17', 4, 2),
(8, 2, '2025-11-18', 3, 4);

-- Inserir Check-ins de Humor (Usuário 3 - Carla Mendes)
INSERT INTO CHECKIN_HUMOR (id_checkin, id_usuario, data_checkin, nivel_humor, nivel_estresse) VALUES
(9, 3, '2025-11-16', 5, 1),
(10, 3, '2025-11-17', 4, 2),
(11, 3, '2025-11-18', 5, 1);

-- Inserir Check-ins de Humor (Usuário 4 - Diego Santos)
INSERT INTO CHECKIN_HUMOR (id_checkin, id_usuario, data_checkin, nivel_humor, nivel_estresse) VALUES
(12, 4, '2025-11-15', 3, 4),
(13, 4, '2025-11-16', 2, 5),
(14, 4, '2025-11-17', 3, 4),
(15, 4, '2025-11-18', 4, 3);

-- Inserir Check-ins de Humor (Usuário 5 - Elisa Oliveira)
INSERT INTO CHECKIN_HUMOR (id_checkin, id_usuario, data_checkin, nivel_humor, nivel_estresse) VALUES
(16, 5, '2025-11-17', 4, 2),
(17, 5, '2025-11-18', 5, 1);

-- ============================================================
-- CONSULTAS ÚTEIS PARA ANÁLISE
-- ============================================================

-- Consulta 1: Listar todos os usuários com seus check-ins mais recentes
-- SELECT u.id_usuario, u.nome, u.email, 
--        MAX(ch.data_checkin) as ultimo_checkin,
--        (SELECT nivel_humor FROM CHECKIN_HUMOR WHERE id_usuario = u.id_usuario ORDER BY data_checkin DESC LIMIT 1) as humor_atual,
--        (SELECT nivel_estresse FROM CHECKIN_HUMOR WHERE id_usuario = u.id_usuario ORDER BY data_checkin DESC LIMIT 1) as estresse_atual
-- FROM USUARIO u
-- LEFT JOIN CHECKIN_HUMOR ch ON u.id_usuario = ch.id_usuario
-- GROUP BY u.id_usuario, u.nome, u.email;

-- Consulta 2: Média de estresse por usuário
-- SELECT u.id_usuario, u.nome, 
--        AVG(ch.nivel_estresse) as estresse_medio,
--        AVG(ch.nivel_humor) as humor_medio
-- FROM USUARIO u
-- LEFT JOIN CHECKIN_HUMOR ch ON u.id_usuario = ch.id_usuario
-- GROUP BY u.id_usuario, u.nome;

-- Consulta 3: Usuários com alto nível de estresse (média >= 4)
-- SELECT u.id_usuario, u.nome, AVG(ch.nivel_estresse) as estresse_medio
-- FROM USUARIO u
-- JOIN CHECKIN_HUMOR ch ON u.id_usuario = ch.id_usuario
-- GROUP BY u.id_usuario, u.nome
-- HAVING AVG(ch.nivel_estresse) >= 4;

