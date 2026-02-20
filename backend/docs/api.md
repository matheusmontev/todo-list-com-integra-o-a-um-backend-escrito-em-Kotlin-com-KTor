# API de Tarefas (Ktor)

## Modelo de resposta

Todas as respostas seguem o mesmo formato:

```json
{
  "data": {},
  "message": "texto descritivo",
  "errors": [
    { "field": "title", "detail": "Title is required" }
  ]
}
```

- `data`: payload principal (objeto, lista ou `null`).
- `message`: mensagem amigável sobre o resultado.
- `errors`: lista de erros de validação/regra (vazia em sucesso).

## Modelo de tarefa

Campos obrigatórios de uma tarefa:

- `id` (`string`)
- `title` (`string`)
- `completed` (`boolean`)
- `createdAt` (`string` em formato ISO-8601)

## Endpoints

### 1) Listar tarefas

`GET /tasks`

**Response 200**

```json
{
  "data": [
    {
      "id": "8f49f7c8-8d6e-403b-9b97-5f4ebf70fd66",
      "title": "Estudar Ktor",
      "completed": false,
      "createdAt": "2026-02-20T19:32:15.508Z"
    }
  ],
  "message": "Tasks loaded successfully",
  "errors": []
}
```

### 2) Criar tarefa

`POST /tasks`

**Request body**

```json
{
  "title": "Implementar endpoint PATCH"
}
```

**Response 201**

```json
{
  "data": {
    "id": "56f2058e-0bb9-4b31-8d7a-f0b0be25e10a",
    "title": "Implementar endpoint PATCH",
    "completed": false,
    "createdAt": "2026-02-20T19:35:41.210Z"
  },
  "message": "Task created successfully",
  "errors": []
}
```

**Response 400 (exemplo de validação)**

```json
{
  "data": null,
  "message": "Validation failed",
  "errors": [
    { "field": "title", "detail": "Title is required" }
  ]
}
```

### 3) Atualizar status

`PATCH /tasks/{id}/status`

**Request body**

```json
{
  "completed": true
}
```

**Response 200**

```json
{
  "data": {
    "id": "56f2058e-0bb9-4b31-8d7a-f0b0be25e10a",
    "title": "Implementar endpoint PATCH",
    "completed": true,
    "createdAt": "2026-02-20T19:35:41.210Z"
  },
  "message": "Task status updated successfully",
  "errors": []
}
```

### 4) Remover tarefa

`DELETE /tasks/{id}`

**Response 200**

```json
{
  "data": null,
  "message": "Task removed successfully",
  "errors": []
}
```

**Response 404**

```json
{
  "data": null,
  "message": "Task not found",
  "errors": [
    { "field": "id", "detail": "No task found for id 56f2058e-0bb9-4b31-8d7a-f0b0be25e10a" }
  ]
}
```
