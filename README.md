# Task-List-API

API do desafil Supreme

O Schema para criação do BD esta no arquivo SQL.

Foi utilizando o framework Spring Boot com JPA para persistencia no BD.

A construção da API seguiu as melhores praticas de desenvolvimento.

Como era um projeto pequeno não foi usado o Hibernate para ORM e sim JPA nativo.

# End-Points
Foi adicionado o arquivo de back do Insomnia com todo os END-POINTS para validação.

Request: /task
Method: POST
Description: Inseri tarefa no BD
JSON: {
	"title": "Titulo da Tarefa",
	"description": "Descrição da tarefa",
	"status": false,
	"created_at": "2021-04-08 00:26:00",
	"updated_at": "2021-04-08 00:26:00",
	"remove_at": null,
	"concluded": null,
	"position": 1
}

Request: /task/{id}
Method: PUT
Description: Altera tarefa no BD
JSON: {
	"title": "Tarefa de Teste Update 2",
	"description": "Descrição da tarefa de teste Up"
}

Request: /task/{id}
Method: DELETE
Description: Remove Task DB

Request: /task
Method: GET
Description: Lista todas as tarefas ativas
JSON: {
	"title": "Tarefa de Teste Update 2",
	"description": "Descrição da tarefa de teste Up"
}

Request: /task/{id}
Method: GET
Description: Informa dados da tarefa

Request: /task/list/concluded
Method: GET
Description: Lista todas as tarefas concluidas

### Features

- [x] Cadastro de Tarefa
- [x] Edição de Tarefa
- [x] Lista de Tarefas Ativas
- [x] Lista de Tarefas Concluidas
- [x] Conclusão de Tarefas
- [x] Reabertura de Tarefas
- [x] Remover Tarefa Ativas ou Concluidas  

