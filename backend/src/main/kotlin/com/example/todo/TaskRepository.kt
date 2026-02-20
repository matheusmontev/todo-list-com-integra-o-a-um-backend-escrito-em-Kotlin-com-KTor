package com.example.todo

import java.time.Instant
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class TaskRepository {
    private val tasks = ConcurrentHashMap<String, Task>()

    fun list(): List<Task> = tasks.values.sortedByDescending { it.createdAt }

    fun create(title: String): Task {
        val task = Task(
            id = UUID.randomUUID().toString(),
            title = title,
            completed = false,
            createdAt = Instant.now().toString(),
        )
        tasks[task.id] = task
        return task
    }

    fun updateStatus(id: String, completed: Boolean): Task? {
        val existing = tasks[id] ?: return null
        val updated = existing.copy(completed = completed)
        tasks[id] = updated
        return updated
    }

    fun remove(id: String): Boolean = tasks.remove(id) != null
}
