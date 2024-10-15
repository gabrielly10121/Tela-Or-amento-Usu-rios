# Projeto de Gerenciamento de Orçamento e Usuários


Este projeto foi desenvolvido com o objetivo de criar um sistema de gerenciamento de orçamentos e controle de usuários. Ele utiliza o framework Spring Boot com Thymeleaf para renderização de páginas dinâmicas e JPA para persistência de dados. O projeto inclui um CRUD completo para usuários e orçamentos, com operações de criação, leitura, atualização e exclusão.


Tecnologias Utilizadas:

Linguagem de Programação: Java

Frameworks:

Spring Boot: Para a criação da aplicação web e gerenciamento de dependências.

Thymeleaf: Para renderização de templates HTML.

Spring Data JPA: Para persistência de dados.

Banco de Dados: H2 (em memória) para desenvolvimento e testes, com suporte para PostgreSQL em produção.

Estilização: CSS puro para estilização das páginas HTML.

Ferramentas de Build: Maven para gerenciamento de dependências e build do projeto.

Outras Bibliotecas:

Lombok: Para reduzir o código boilerplate (getters, setters, etc.).

Kotlin: Suporte para Kotlin no projeto.

IDE Utilizada:

O desenvolvimento foi realizado utilizando a IntelliJ IDEA, que proporcionou um ambiente de desenvolvimento integrado e eficiente para a construção das funcionalidades do projeto.


Funcionalidades:

Tela de Cadastro de Usuários: Permite o cadastro de novos usuários, com validação de campos obrigatórios.

Listagem de Usuários: Exibe uma tabela com todos os usuários cadastrados, permitindo a visualização de seus IDs e nomes.

CRUD de Usuários: Funcionalidades completas de criação, leitura, atualização e exclusão de usuários.

Tela de Cadastro de Orçamentos: Permite o cadastro de orçamentos para Pessoa Física e Pessoa Jurídica, com validação de CPF/CNPJ e cálculo automático de ICMS.

Listagem de Orçamentos: Exibe uma tabela com todos os orçamentos cadastrados, incluindo informações como nome do comprador, CPF/CNPJ, valor do orçamento, valor do ICMS e o usuário responsável.

Configurações de Banco de Dados

Banco de Dados H2: Utilizado para desenvolvimento e testes, com console web habilitado em /h2-console.

PostgreSQL: Configuração disponível para uso em produção, basta descomentar as linhas no arquivo application.properties.

Como Executar:

Clone o repositório.

Execute o comando mvn spring-boot:run para iniciar a aplicação.

Acesse a aplicação no navegador em http://localhost:8081/usuarios para a tela de usuários ou http://localhost:8081/orcamentos para a tela de orçamentos.
