# Todo list com integração backend (Ktor) e frontend

Este repositório agora contém uma base para:

- Backend em **Ktor** com endpoints REST de tarefas.
- Contrato de resposta padronizado com `data`, `message` e `errors`.
- Serviço HTTP único no frontend para centralizar integrações da feature de tarefas.

## Estrutura

- `backend/src/main/kotlin/com/example/todo/` — implementação da API.
- `backend/docs/api.md` — documentação de endpoints com exemplos de request/response.
- `frontend/src/services/tasksApi.ts` — cliente HTTP centralizado de tarefas.

## Campos obrigatórios de tarefa

A entidade de tarefa exposta pela API possui obrigatoriamente:

- `id`
- `title`
- `completed`
- `createdAt`

Para exemplos completos de payloads, consulte [backend/docs/api.md](backend/docs/api.md).
