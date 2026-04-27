package com.example.baseandroidapp.core.result

/**
 * Lightweight result wrapper for layers that prefer not to throw across
 * boundaries. Map a thrown exception to [Failure] at the data-layer edge.
 */
sealed interface Result<out T> {
    data class Success<T>(val value: T) : Result<T>
    data class Failure(val error: Throwable) : Result<Nothing>
}

inline fun <T, R> Result<T>.map(transform: (T) -> R): Result<R> = when (this) {
    is Result.Success -> Result.Success(transform(value))
    is Result.Failure -> this
}

inline fun <T> runCatchingResult(block: () -> T): Result<T> = try {
    Result.Success(block())
} catch (t: Throwable) {
    Result.Failure(t)
}
