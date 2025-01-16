# Guia de Implementação - Sistema Hortifruti

## Estrutura do Projeto

### Camadas da Aplicação

#### 1. DAO (Data Access Object)
- Responsável pelo acesso direto ao banco de dados
- Contém as classes que executam operações CRUD (Create, Read, Update, Delete)
- Isola a lógica de persistência do resto da aplicação
- Exemplo: `FornecedorDAO.java` lida com operações de banco de dados relacionadas a fornecedores

#### 2. MODEL
- Contém as classes que representam as entidades do sistema
- Define a estrutura dos dados e suas validações
- São objetos simples (POJOs - Plain Old Java Objects)
- Exemplo: `Fornecedor.java` define os atributos e comportamentos de um fornecedor

#### 3. SERVICE
- Contém a lógica de negócio da aplicação
- Coordena as operações entre DAOs e outras camadas
- Implementa regras de negócio complexas
- Exemplo: `VendaService.java` coordena o processo de venda, incluindo validações, cálculos e atualizações de estoque

#### 4. UTIL
- Contém classes utilitárias usadas em todo o projeto
- Funções comuns reutilizáveis
- Validadores, formatadores, conversores
- Exemplo: `CNPJValidator.java` para validar formato de CNPJ

#### 5. RESOURCES
- Armazena arquivos não-Java necessários para a aplicação
- Arquivos de configuração
- Templates
- Arquivos estáticos (imagens, CSS, JavaScript)
- Exemplo: `application.properties` com configurações do banco de dados

### Estrutura de Arquivos
```
src/
├── main/
│   ├── java/com/hortifruti/
│   │   ├── model/
│   │   │   ├── BaseModel.java       # Classe base com id e timestamps
│   │   │   ├── Produto.java         # Produtos (frutas, verduras, legumes)
│   │   │   ├── Fornecedor.java      # Fornecedores com validação CNPJ
│   │   │   ├── Venda.java           # Vendas com itens e pagamento
│   │   │   └── ItemVenda.java       # Itens de venda
│   │   ├── dao/
│   │   │   ├── BaseDAO.java         # Interface genérica CRUD
│   │   │   ├── ProdutoDAO.java      # Operações de produtos
│   │   │   ├── FornecedorDAO.java   # Operações de fornecedores
│   │   │   └── VendaDAO.java        # Operações de vendas
│   │   ├── service/
│   │   │   ├── ProdutoService.java    # Regras de produtos
│   │   │   ├── FornecedorService.java # Regras de fornecedores
│   │   │   └── VendaService.java      # Regras de vendas
│   │   └── util/
│   │       └── DatabaseConfig.java   # Configuração do banco
│   └── resources/
│       ├── database.properties      # Configurações de conexão
│       └── schema.sql              # Schema do banco
└── test/java/com/hortifruti/
    └── model/
        ├── ProdutoTest.java        # Testes de produto
        ├── FornecedorTest.java     # Testes de fornecedor
        ├── VendaTest.java          # Testes de venda
        └── ItemVendaTest.java      # Testes de item de venda
```

## Implementação das Classes

### 1. Models

#### BaseModel.java
```java
- Long id
- LocalDateTime createdAt
- LocalDateTime updatedAt
- Getters/Setters
```

#### Produto.java
```java
- String nome
- Enum categoria (FRUTA, VERDURA, LEGUME)
- BigDecimal precoKg
- BigDecimal estoque
- LocalDate validade
- Long fornecedorId

Validar:
- Nome não vazio
- Preço > 0
- Estoque >= 0
- Validade futura
```

#### Fornecedor.java
```java
- String nome
- String cnpj
- String telefone
- String email
- String endereco

Validar:
- CNPJ válido
- Email válido
- Nome obrigatório
```

#### Venda.java
```java
- LocalDateTime data
- List<ItemVenda> itens
- BigDecimal valorTotal
- Enum formaPagamento

Métodos:
- calcularTotal()
- adicionarItem()
- removerItem()
```

#### ItemVenda.java
```java
- Produto produto
- BigDecimal quantidade
- BigDecimal precoUnitario
- BigDecimal subtotal

Métodos:
- calcularSubtotal()
- atualizarQuantidade()
- validarQuantidade()

Validar:
- Quantidade > 0
- Produto não nulo
- Preço válido
```

### 2. DAOs

#### BaseDAO.java
```java
Interface genérica:
- criar(T)
- buscarPorId(Long)
- buscarTodos()
- atualizar(T)
- deletar(Long)
```

#### ProdutoDAO.java
```java
Métodos:
- buscarPorCategoria()
- buscarPorValidade()
- atualizarEstoque()

SQL:
- INSERT INTO produtos ...
- SELECT * FROM produtos WHERE ...
- UPDATE produtos SET ...
```

### 3. Services

#### ProdutoService.java
```java
Regras:
- Validar produto
- Controlar estoque
- Alertar validade
- Calcular preços
```

#### FornecedorService.java
```java
Regras:
- Validar CNPJ
- Verificar unicidade
- Manter histórico
```

#### VendaService.java
```java
Regras:
- Processar venda
- Atualizar estoque
- Calcular total
- Gerar comprovante
```

### 4. Banco de Dados

#### database.properties
```properties
db.url=jdbc:postgresql://localhost:5432/hortifruti
db.username=postgres
db.password=postgres
db.pool.maxSize=10
```

#### schema.sql
```sql
CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(20) NOT NULL,
    preco_kg DECIMAL(10,2) NOT NULL,
    estoque DECIMAL(10,2) NOT NULL,
    validade DATE NOT NULL,
    fornecedor_id BIGINT REFERENCES fornecedores(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE fornecedores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    endereco TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE vendas (
    id SERIAL PRIMARY KEY,
    data TIMESTAMP NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    forma_pagamento VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE itens_venda (
    id SERIAL PRIMARY KEY,
    venda_id BIGINT REFERENCES vendas(id),
    produto_id BIGINT REFERENCES produtos(id),
    quantidade DECIMAL(10,2) NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Cronograma de Implementação

1. **Configuração Inicial (Dia 1)**
   - Criar projeto Maven (pom.xml)
   - Criar .gitignore (incluir database.properties)
   - Configurar estrutura de pastas
   - Criar arquivo de exemplo database.properties.example
   - Criar e testar schema do banco

2. **Models e Validações (Dia 2)**
   - Implementar BaseModel
   - Criar enums (Categoria, FormaPagamento)
   - Implementar models com validações:
     * Produto
     * Fornecedor
     * Venda
     * ItemVenda
   - Criar testes unitários para cada model

3. **Conexão e DAOs (Dia 3)**
   - Implementar DatabaseConfig
   - Criar BaseDAO com operações comuns
   - Implementar DAOs específicos:
     * ProdutoDAO
     * FornecedorDAO
     * VendaDAO
   - Criar testes de integração

4. **Services e Regras (Dia 4)**
   - Criar interfaces dos services
   - Implementar regras de negócio:
     * ProdutoService (estoque, validade)
     * FornecedorService (CNPJ, contatos)
     * VendaService (pagamento, cálculos)
   - Adicionar logs de operações
   - Criar testes dos services

5. **Interface e Fluxos (Dia 5)**
   - Criar menu principal
   - Implementar operações CRUD
   - Adicionar tratamento de erros
   - Criar fluxos completos:
     * Cadastro de produtos
     * Entrada de mercadoria
     * Processo de venda

6. **Finalização (Dia 6)**
   - Implementar relatórios
   - Adicionar documentação (JavaDoc)
   - Criar scripts de exemplo
   - Testar fluxos completos
   - Preparar ambiente de produção

7. **Deploy (Dia 7)**
   - Configurar ambiente de produção
   - Criar scripts de deploy
   - Realizar deploy
   - Criar documentação de deploy

8. **Manutenção (Dia 8)**
   - Adicionar documentação (GitHub)
   - Realizar manutenção de código
   - Criar documentação de manutenção

9. **Testes de Aceitação (Dia 9)**
   - Criar testes de aceitação
   - Implementar fluxos de aceitação
   - Criar documentação de testes de aceitação

10. **Documentação Final (Dia 10)**
   - Criar documentação final
   - Realizar deploy final
   - Criar documentação de deploy final