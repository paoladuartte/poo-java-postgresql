# POO com Java e PostgreSQL

Projeto acadêmico desenvolvido durante meus estudos em Ciência da Computação, com o objetivo de praticar Programação Orientada a Objetos (POO) em Java e a integração com banco de dados PostgreSQL utilizando JDBC.

## 🎯 Objetivo

Desenvolver uma aplicação Java para gerenciar informações de pessoas, aplicando conceitos de programação e operações de manipulação de dados em um banco relacional.

## 🛠️ Tecnologias utilizadas

- **Java**: desenvolvimento da aplicação.
- **Programação Orientada a Objetos (POO)**: organização do código em classes e métodos.
- **JDBC**: comunicação entre Java e o banco de dados.
- **PostgreSQL**: armazenamento e gerenciamento dos dados.
- **Eclipse IDE**: ambiente de desenvolvimento.

## ⚙️ Funcionalidades

A aplicação possui um menu interativo no console que permite:

- **Cadastrar:** inserir os dados de uma pessoa.
- **Consultar:** listar as pessoas cadastradas.
- **Atualizar:** alterar informações de um cadastro existente.
- **Excluir:** remover um cadastro pelo ID.

Os dados gerenciados incluem nome, data de nascimento, cidade, rua e número.

## 🗄️ Estrutura do banco de dados

O projeto utiliza uma tabela chamada `pessoa`, com os seguintes campos:

| Campo | Tipo | Descrição |
|---|---|---|
| `idPes` | SERIAL | Identificador gerado automaticamente |
| `nomePes` | VARCHAR(80) | Nome da pessoa |
| `DataNascPes` | DATE | Data de nascimento |
| `cidadePes` | VARCHAR(80) | Cidade |
| `ruaPes` | VARCHAR(80) | Rua |
| `numeroPes` | INT | Número do endereço |

### Criação da tabela

```sql
CREATE TABLE Pessoa (
    idPes SERIAL NOT NULL,
    nomePes VARCHAR(80) NOT NULL,
    DataNascPes DATE,
    cidadePes VARCHAR(80),
    ruaPes VARCHAR(80),
    numeroPes INT,
    PRIMARY KEY (idPes)
);
```

## 🚀 Como executar o projeto

1. Instale o Java JDK, o Eclipse IDE e o PostgreSQL.
2. Crie um banco de dados chamado `Agenda`.
3. Execute o comando SQL acima para criar a tabela `Pessoa`.
4. Adicione o driver JDBC do PostgreSQL ao projeto.
5. Configure a conexão com o banco na classe `ConnectionFactory`, utilizando o endereço, usuário e senha do seu ambiente.
6. Execute a classe `Principal` no Eclipse.
7. Utilize o menu exibido no console para cadastrar, listar, alterar e excluir registros.

**Importante:** não compartilhe senhas reais do banco de dados no repositório.

## 📚 Aprendizados

Este projeto contribuiu para a prática de:

- Programação Orientada a Objetos em Java.
- Integração de aplicações Java com bancos de dados relacionais.
- Utilização de JDBC e `PreparedStatement`.
- Execução de comandos SQL para manipulação de dados.
- Tratamento de exceções.
- Organização de código em classes, separando a aplicação das operações de acesso aos dados.
