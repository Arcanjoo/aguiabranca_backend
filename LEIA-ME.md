# ÁGUIA BRANCA - BACKEND API

Esta é a API Rest responsável por gerir toda a regra de negócio da aplicação de vistoria e manutenção de frotas. O sistema lida com a autenticação de utilizadores, gestão de veículos, registo de checklists pré-viagem e controlo de estados de manutenções.

## TECNOLOGIAS UTILIZADAS
* **Linguagem:** Java
* **Framework:** Spring Boot
* **Segurança:** Spring Security com autenticação via Token JWT
* **Base de Dados:** MongoDB (NoSQL - Cloud/MongoDB Atlas)
* **Gestão de Dependências:** Maven

## ARQUITETURA E ORGANIZAÇÃO
O projeto foi estruturado utilizando o padrão de arquitetura em camadas para garantir a separação de responsabilidades, escalabilidade e fácil manutenção:

* **controller/:** Camada de exposição dos endpoints REST (ex: AuthController, VeiculoController, ManutencaoController).
* **service/:** Camada contendo a regra de negócio e validações lógicas da aplicação.
* **repository/:** Camada de persistência de dados (interfaces Spring Data MongoDB).
* **model/:** Entidades de domínio mapeadas para coleções da base de dados.
* **dto/:** Objetos de Transferência de Dados para isolar o domínio dos pedidos externos (Payloads e Responses).
* **security/:** Configurações de filtros, cors, encriptação de palavras-passe e geração/validação de tokens JWT.
* **exception/:** Tratamento global de erros para retorno padronizado nas APIs.

## PRÉ-REQUISITOS
Para correr este projeto localmente, precisará apenas de ter instalado na sua máquina:
* JDK 17 (ou superior)
* Maven

## COMO EXECUTAR O PROJETO LOCALMENTE

### CONFIGURAÇÃO DA BASE DE DADOS (ONLINE)
A aplicação está configurada para comunicar diretamente com a base de dados alojada na nuvem (MongoDB Atlas). 

**Nota para o avaliador:** O ficheiro `src/main/resources/application.properties` já se encontra devidamente configurado com a URI de conexão funcional. Além disso, o cluster do MongoDB Atlas foi configurado com a regra Network Access `0.0.0.0/0`, permitindo conexões de qualquer IP. Isto garante que o projeto pode ser executado e testado localmente sem bloqueios de firewall.

A string de conexão configurada no properties segue a estrutura:
`spring.data.mongodb.uri=mongodb+srv://joaopdr924_db_user:aguia1234@cluster0.d91qif4.mongodb.net/aguiabranca_db?retryWrites=true&w=majority&appName=Cluster0`

### EXECUTAR VIA LINHA DE COMANDOS (TERMINAL)
Na raiz do projeto (onde se encontra o ficheiro `pom.xml` e o executável `mvnw`), execute o comando abaixo para compilar e iniciar o servidor:

**No Windows:**
`mvnw.cmd spring-boot:run`

**No Linux/Mac:**
`./mvnw spring-boot:run`

### EXECUTAR VIA IDE (IntelliJ, Eclipse, VS Code)
1. Importe o projeto como um projeto Maven.
2. Aguarde a transferência das dependências.
3. Localize o ficheiro `AguiabrancaBackendApplication.java` e execute-o (Run).

## ACEDER À API
Por predefinição, o servidor local iniciará na porta 8080 conectado à base de dados online.
* **URL base local:** http://localhost:8080