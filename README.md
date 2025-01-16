# Sistema de Gerenciamento de Hortifruti

## Sobre o Projeto
Sistema para gerenciamento de hortifruti com controle de produtos, fornecedores e vendas.

## Funcionalidades
1. Gestão de Produtos
   - Cadastro de frutas, verduras e legumes
   - Controle de estoque
   - Alertas de validade
   - Preços por kg

2. Gestão de Fornecedores
   - Cadastro completo
   - Validação de CNPJ
   - Histórico de fornecimento

3. Gestão de Vendas
   - Registro de vendas
   - Múltiplos itens
   - Formas de pagamento
   - Relatórios

## Tecnologias
- Java 17
- PostgreSQL
- Maven
- HikariCP
- JUnit 5
- SLF4J

## Configuração
1. Banco de Dados
   ```sql
   createdb hortifruti
   psql hortifruti < schema.sql
   ```

2. Projeto
   ```bash
   mvn clean install
   mvn exec:java
   ```

## Documentação
- Consulte IMPLEMENTATION_GUIDE.md para o passo a passo
- Javadoc nos códigos
- Testes unitários