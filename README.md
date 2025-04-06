2TDSPJ - Análise e Desenvolvimento de Sistemas

Integrantes:
Eduardo do Nascimento Barriviera - RM555309
Thiago Lima de Freitas - RM556795


API de Catálogo de Carros.

API desenvolvida em Java + Spring Boot para gerenciar um catálogo de carros, permitindo consultas e operações CRUD.

- Tecnologias Utilizadas:
Java 17
Spring Boot
H2 Database (Banco de dados em memória)
JUnit + Mockito (Testes)
Maven

- Endpoints da API
- Consultas
GET /carros/potencia → Retorna os 10 carros mais potentes (ordem decrescente).
GET /carros/economia → Retorna os 10 carros mais econômicos.
GET /carros/eletricos → Lista todos os carros elétricos.
GET /carros/{id} → Retorna os detalhes de um carro específico.

- CRUD
POST /carros → Adiciona um novo carro.
PUT /carros/{id} → Atualiza um carro existente.
DELETE /carros/{id} → Remove um carro do catálogo

- Testes Unitários

-----------------------------------/

Instruções para Deploy e Teste

1. Pré-requisitos
- Java 17
- Maven
- Git

2. Clonando o Repositório
git clone https://github.com/seu-usuario/catalogo-carros.git
cd catalogo-carros

3. Build do Projeto
mvn clean install

4. Rodando o Projeto
mvn spring-boot:run
A API estará disponível em: http://localhost:8080

5. Testando a API

Testando os Endpoints com cURL
GET /carros/potencia:
curl -X GET http://localhost:8080/carros/potencia

GET /carros/{id}:
curl -X GET http://localhost:8080/carros/1

POST /carros:
curl -X POST http://localhost:8080/carros -H "Content-Type: application/json" -d '{
  "marca": "Toyota",
  "modelo": "Corolla",
  "ano": 2022,
  "potencia": 170,
  "economia": 12.5,
  "tipo": "combustao",
  "preco": 120000
}'

Rodando os Testes Automatizados
mvn test

Gerando Relatório de Cobertura de Testes
mvn jacoco:report
Abrir: target/site/jacoco/index.html

6. Deploy em Produção

#Gerar o JAR
mvn package

Rodar o JAR
java -jar target/catalogo-carros-1.0.jar
