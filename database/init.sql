CREATE TABLE IF NOT EXISTS usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    email VARCHAR(160) NOT NULL UNIQUE,
    perfil VARCHAR(30) NOT NULL CHECK (perfil IN ('USUARIO','ANALISTA','ADMIN')),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS categorias (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS equipamentos (
    id BIGSERIAL PRIMARY KEY,
    patrimonio VARCHAR(50) NOT NULL UNIQUE,
    tipo VARCHAR(60) NOT NULL,
    fabricante VARCHAR(80),
    modelo VARCHAR(100),
    numero_serie VARCHAR(100),
    usuario_id BIGINT REFERENCES usuarios(id)
);

CREATE TABLE IF NOT EXISTS chamados (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(160) NOT NULL,
    descricao TEXT NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'ABERTO',
    prioridade VARCHAR(20) NOT NULL DEFAULT 'MEDIA',
    usuario_id BIGINT NOT NULL REFERENCES usuarios(id),
    analista_id BIGINT REFERENCES usuarios(id),
    categoria_id BIGINT NOT NULL REFERENCES categorias(id),
    equipamento_id BIGINT REFERENCES equipamentos(id),
    diagnostico TEXT,
    solucao TEXT,
    prazo_sla TIMESTAMP,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    resolvido_em TIMESTAMP,
    encerrado_em TIMESTAMP
);

CREATE TABLE IF NOT EXISTS diagnosticos (
    id BIGSERIAL PRIMARY KEY,
    chamado_id BIGINT NOT NULL REFERENCES chamados(id) ON DELETE CASCADE,
    analista_id BIGINT NOT NULL REFERENCES usuarios(id),
    causa_raiz TEXT NOT NULL,
    procedimentos TEXT,
    resultado TEXT,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS historico_chamados (
    id BIGSERIAL PRIMARY KEY,
    chamado_id BIGINT NOT NULL REFERENCES chamados(id) ON DELETE CASCADE,
    usuario_id BIGINT REFERENCES usuarios(id),
    status_anterior VARCHAR(30),
    status_novo VARCHAR(30),
    observacao TEXT,
    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO usuarios (nome, email, perfil) VALUES
('Victor Hugo', 'victor@supportdesk.local', 'ADMIN'),
('Ana Souza', 'ana@supportdesk.local', 'ANALISTA'),
('Carlos Lima', 'carlos@supportdesk.local', 'ANALISTA'),
('Mariana Costa', 'mariana@supportdesk.local', 'USUARIO')
ON CONFLICT (email) DO NOTHING;

INSERT INTO categorias (nome, descricao) VALUES
('Hardware', 'Computadores, monitores, periféricos e componentes'),
('Software', 'Sistemas operacionais e aplicações'),
('Rede', 'Internet, Wi-Fi, DNS, IP e conectividade'),
('Acesso', 'Contas, permissões e autenticação'),
('Impressão', 'Impressoras e filas de impressão')
ON CONFLICT (nome) DO NOTHING;

INSERT INTO equipamentos (patrimonio, tipo, fabricante, modelo, numero_serie, usuario_id)
SELECT 'NB-1001', 'Notebook', 'Lenovo', 'ThinkPad E14', 'SN-LEN-001', id
FROM usuarios WHERE email = 'mariana@supportdesk.local'
ON CONFLICT (patrimonio) DO NOTHING;
