package com.example.todo

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: String,
    val title: String,
    val completed: Boolean,
    val createdAt: String,
)

@Serializable
data class CreateTaskRequest(
    val title: String,
)

@Serializable
data class UpdateTaskStatusRequest(
    val completed: Boolean,
)

@Serializable
data class ApiError(
    val field: String? = null,
    val detail: String,
)

@Serializable
data class ApiResponse<T>(
    val data: T? = null,
    val message: String,
    val errors: List<ApiError> = emptyList(),
)
