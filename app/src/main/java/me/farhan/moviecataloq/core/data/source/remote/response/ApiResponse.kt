package me.farhan.moviecataloq.core.data.source.remote.response

/**
 * @author farhan
 * created at at 12:08 on 28/11/20.
 */
sealed class ApiResponse<out R> {
  data class Success<out T>(val data: T) : ApiResponse<T>()
  data class Error(val errorMessage: String) : ApiResponse<Nothing>()
  object Empty : ApiResponse<Nothing>()
}