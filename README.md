# TODO List com backend em Kotlin (Ktor) + frontend

Este projeto integra um backend em **Kotlin com Ktor** e um frontend web para gerenciamento de tarefas.

## Pré-requisitos

Antes de iniciar, instale:

- **JDK 17+** (recomendado: JDK 17 ou 21)
- **Gradle** (ou use o wrapper `./gradlew` do projeto)
- **Node.js 18+** e **npm** (runtime do frontend)

Comandos úteis para validar versões:

```bash
java -version
gradle -v
node -v
npm -v
```

## Como iniciar o projeto

> Execute backend e frontend em terminais separados.

### 1) Iniciar backend (Ktor)

Na raiz do backend:

```bash
./gradlew run
```

Alternativa (se aplicável ao seu projeto):

```bash
./gradlew :backend:run
```

### 2) Iniciar frontend

Na raiz do frontend:

```bash
npm install
npm run dev
```

Alternativas comuns (dependendo do setup):

```bash
npm start
# ou
npm run start
```

## Variáveis de ambiente necessárias

Ajuste os valores conforme seu ambiente. Exemplo:

### Backend

```env
# Porta do backend Ktor
PORT=8080

# Ambiente de execução
KTOR_ENV=dev

# URL base permitida para o frontend (CORS)
FRONTEND_URL=http://localhost:5173
```

### Frontend

```env
# URL da API do backend
VITE_API_BASE_URL=http://localhost:8080
# (ou, em apps React sem Vite)
# REACT_APP_API_BASE_URL=http://localhost:8080
```

> Sugestão: criar arquivos `.env` (frontend) e variáveis no ambiente de execução do backend.

## Fluxo completo para teste manual

Com backend e frontend em execução:

1. Abra o frontend no navegador (ex.: `http://localhost:5173`).
2. **Criar tarefa**
   - Digite um título no campo de nova tarefa.
   - Clique em **Adicionar/Criar**.
   - Valide se a tarefa aparece na lista.
3. **Marcar tarefa como concluída**
   - Clique no checkbox (ou ação equivalente) da tarefa criada.
   - Valide mudança visual de status (ex.: riscado, badge “concluída” etc.).
4. **Remover tarefa**
   - Clique em **Remover/Excluir** na tarefa.
   - Valide se ela sai da lista.
5. (Opcional) Recarregue a página para verificar persistência dos dados.

## Testes automatizados

Se houver testes configurados no projeto, execute separadamente:

### Backend

```bash
./gradlew test
```

### Frontend

```bash
npm test
# ou
npm run test
```

Se houver testes e2e:

```bash
npm run test:e2e
```
