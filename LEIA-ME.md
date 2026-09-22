# ÁGUIA BRANCA - BACKEND API

Esta é a API Rest responsável por gerir toda a regra de negócio da aplicação de vistoria, manutenção de frotas e avaliação automatizada de ideias com Inteligência Artificial. O sistema lida com a autenticação de utilizadores, gestão de veículos, registo de checklists pré-viagem, controlo de estados de manutenções e integração nativa com o Google Gemini.

## TECNOLOGIAS UTILIZADAS
* **Linguagem:** Java
* **Framework:** Spring Boot
* **Segurança:** Spring Security com autenticação via Token JWT
* **Base de Dados:** MongoDB (NoSQL - Cloud/MongoDB Atlas)
* **Inteligência Artificial:** Google Gemini API (Modelo Flash) com autenticação via Bearer Token
* **Gestão de Dependências:** Maven

## ARQUITETURA E ORGANIZAÇÃO
O projeto foi estruturado utilizando o padrão de arquitetura em camadas para garantir a separação de responsabilidades, escalabilidade e fácil manutenção:

* **controller/:** Camada de exposição dos endpoints REST (ex: AuthController, VeiculoController, ManutencaoController, IdeiaController).
* **service/:** Camada contendo a regra de negócio, validações lógicas e integração HTTP com a API do Gemini.
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

### CONFIGURAÇÃO DA BASE DE DADOS E IA (ONLINE)
A aplicação está configurada para comunicar diretamente com a base de dados alojada na nuvem (MongoDB Atlas) e com os serviços de Inteligência Artificial do Google Gemini.

**Nota para o avaliador:** O ficheiro `src/main/resources/application.properties` já se encontra devidamente configurado com a URI de conexão do MongoDB e a chave de API segura do Gemini.

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
Por predefinição, o servidor local iniciará na porta 8080 conectado à base de dados online e pronto para receber requisições do aplicativo Android e do portal de inovação[cite: 14].
* **URL base local:** http://localhost:8080[cite: 14]