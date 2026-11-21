# ClinicFlow 🏥

API REST para gerenciamento de uma clínica médica, desenvolvida com Java e Spring Boot.

O **ClinicFlow** é um projeto desenvolvido com o objetivo de aplicar conceitos de desenvolvimento backend, arquitetura de aplicações, persistência de dados e construção de APIs REST, simulando as necessidades de gerenciamento de uma clínica médica.

> 🚧 Projeto em desenvolvimento.

## 🛠️ Tecnologias utilizadas

- **Java** — linguagem principal.
- **Spring Boot** — desenvolvimento da API REST.
- **Spring Data JPA** — persistência e acesso aos dados.
- **Spring Security** — configuração de segurança.
- **PostgreSQL** — banco de dados relacional.
- **Flyway** — gerenciamento de migrations.
- **Docker** — execução do banco de dados em container.
- **Maven** — gerenciamento de dependências e build.
- **Swagger / OpenAPI** — documentação dos endpoints.

## 📌 Funcionalidades implementadas

### Gestão de pacientes

- Cadastro de pacientes.
- Listagem de pacientes ativos.
- Busca de paciente por identificador.
- Atualização dos dados cadastrais.
- Desativação lógica de pacientes.
- Reativação de pacientes.
- Validação de e-mail e telefone duplicados.
- Criptografia de senhas com BCrypt.
- Tratamento global de exceções.
- Validação dos dados de entrada.

## 🗂️ Estrutura do projeto

```text
clinicflow-api/
├── clinicflow-api/
│   └── src/
│       ├── main/
│       │   ├── java/br/com/clinicflow/api/
│       │   │   ├── config/
│       │   │   ├── doctor/
│       │   │   ├── patient/
│       │   │   │   ├── controller/
│       │   │   │   ├── domain/
│       │   │   │   ├── dto/
│       │   │   │   ├── repository/
│       │   │   │   └── service/
│       │   │   ├── shared/
│       │   │   │   └── exception/
│       │   │   └── user/
│       │   └── resources/
│       │       ├── db/migration/
│       │       └── application.properties
│       └── test/
├── .env.example
├── .gitignore
├── docker-compose.yaml
└── README.md
```

## ⚙️ Como executar o projeto

### Pré-requisitos

- Java instalado.
- Docker Desktop.
- Git.

### 1. Clonar o repositório

```bash
git clone https://github.com/luizjardel/clinicflow-api.git
```

```bash
cd clinicflow-api
```

### 2. Configurar as variáveis de ambiente

Copie o arquivo de exemplo:

```bash
copy .env.example .env
```

Abra o arquivo `.env` e configure sua senha:

```env
POSTGRES_PASSWORD=sua_senha
```

### 3. Iniciar o banco de dados

```bash
docker compose up -d
```

O PostgreSQL será executado em um container Docker, utilizando a porta `5433` do computador.

### 4. Executar a aplicação

No PowerShell, configure a mesma senha utilizada no `.env`:

```powershell
$env:POSTGRES_PASSWORD="sua_senha"
```

Entre na pasta da aplicação:

```powershell
cd clinicflow-api
```

Execute:

```powershell
.\mvnw.cmd spring-boot:run
```

As migrations do Flyway serão executadas durante a inicialização da aplicação.

## 📖 Documentação da API

Com a aplicação em execução, acesse:

- Swagger UI: http://localhost:8080/swagger
- OpenAPI: http://localhost:8080/api-docs

## 🗺️ Próximas funcionalidades

- [ ] Cadastro e gerenciamento de médicos.
- [ ] Gerenciamento de especialidades médicas.
- [ ] Definição da disponibilidade semanal dos médicos.
- [ ] Agendamento de consultas.
- [ ] Cancelamento e reagendamento de consultas.
- [ ] Controle de acesso por perfil de usuário.
- [ ] Autenticação com JWT.
- [ ] Desenvolvimento do frontend.

## 👨‍💻 Autor

**Luiz Jardel**

Estudante de Análise e Desenvolvimento de Sistemas, com foco em desenvolvimento de software e backend Java.

[GitHub](https://github.com/luizjardel)

---

Desenvolvido como projeto de estudo e portfólio.
