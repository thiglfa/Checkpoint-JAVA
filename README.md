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
