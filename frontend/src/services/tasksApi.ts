export type Task = {
  id: string;
  title: string;
  completed: boolean;
  createdAt: string;
};

export type ApiError = {
  field?: string;
  detail: string;
};

export type ApiResponse<T> = {
  data: T | null;
  message: string;
  errors: ApiError[];
};

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL ?? "http://localhost:8080";

async function request<T>(path: string, options?: RequestInit): Promise<ApiResponse<T>> {
  const response = await fetch(`${API_BASE_URL}${path}`, {
    headers: {
      "Content-Type": "application/json",
      ...(options?.headers ?? {}),
    },
    ...options,
  });

  const payload = (await response.json()) as ApiResponse<T>;

  if (!response.ok) {
    const reason = payload.message || "Request failed";
    throw new Error(reason);
  }

  return payload;
}

export const tasksApi = {
  list: () => request<Task[]>("/tasks"),

  create: (title: string) =>
    request<Task>("/tasks", {
      method: "POST",
      body: JSON.stringify({ title }),
    }),

  updateStatus: (id: string, completed: boolean) =>
    request<Task>(`/tasks/${id}/status`, {
      method: "PATCH",
      body: JSON.stringify({ completed }),
    }),

  remove: (id: string) =>
    request<void>(`/tasks/${id}`, {
      method: "DELETE",
    }),
};
