package com.example.todo

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.patch
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.taskRoutes(repository: TaskRepository) {
    route("/tasks") {
        get {
            call.respond(
                ApiResponse(
                    data = repository.list(),
                    message = "Tasks loaded successfully",
                ),
            )
        }

        post {
            val payload = runCatching { call.receive<CreateTaskRequest>() }.getOrNull()
            if (payload == null || payload.title.isBlank()) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    ApiResponse<Unit>(
                        message = "Validation failed",
                        errors = listOf(ApiError(field = "title", detail = "Title is required")),
                    ),
                )
                return@post
            }

            val task = repository.create(payload.title.trim())
            call.respond(
                HttpStatusCode.Created,
                ApiResponse(
                    data = task,
                    message = "Task created successfully",
                ),
            )
        }

        patch("/{id}/status") {
            val id = call.parameters["id"]
            if (id.isNullOrBlank()) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    ApiResponse<Unit>(
                        message = "Validation failed",
                        errors = listOf(ApiError(field = "id", detail = "Task id is required")),
                    ),
                )
                return@patch
            }

            val payload = runCatching { call.receive<UpdateTaskStatusRequest>() }.getOrNull()
            if (payload == null) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    ApiResponse<Unit>(
                        message = "Validation failed",
                        errors = listOf(ApiError(field = "completed", detail = "Completed flag is required")),
                    ),
                )
                return@patch
            }

            val updated = repository.updateStatus(id, payload.completed)
            if (updated == null) {
                call.respond(
                    HttpStatusCode.NotFound,
                    ApiResponse<Unit>(
                        message = "Task not found",
                        errors = listOf(ApiError(field = "id", detail = "No task found for id $id")),
                    ),
                )
                return@patch
            }

            call.respond(
                ApiResponse(
                    data = updated,
                    message = "Task status updated successfully",
                ),
            )
        }

        delete("/{id}") {
            val id = call.parameters["id"]
            if (id.isNullOrBlank()) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    ApiResponse<Unit>(
                        message = "Validation failed",
                        errors = listOf(ApiError(field = "id", detail = "Task id is required")),
                    ),
                )
                return@delete
            }

            if (!repository.remove(id)) {
                call.respond(
                    HttpStatusCode.NotFound,
                    ApiResponse<Unit>(
                        message = "Task not found",
                        errors = listOf(ApiError(field = "id", detail = "No task found for id $id")),
                    ),
                )
                return@delete
            }

            call.respond(
                ApiResponse<Unit>(
                    message = "Task removed successfully",
                ),
            )
        }
    }
}
