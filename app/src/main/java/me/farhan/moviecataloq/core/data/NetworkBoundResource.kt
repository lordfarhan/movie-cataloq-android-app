package me.farhan.moviecataloq.core.data

import kotlinx.coroutines.flow.*
import me.farhan.moviecataloq.core.data.source.remote.response.ApiResponse
import me.farhan.moviecataloq.core.util.AppExecutors
import me.farhan.moviecataloq.vo.Resource

/**
 * @author farhan
 * created at at 12:07 on 28/11/20.
 */
abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {

  private var result: Flow<Resource<ResultType>> = flow {
    emit(Resource.Loading())
    val dbSource = loadFromDB().first()
    if (shouldFetch(dbSource)) {
      emit(Resource.Loading())
      when (val apiResponse = createCall().first()) {
        is ApiResponse.Success -> {
          saveCallResult(apiResponse.data)
          emitAll(loadFromDB().map { Resource.Success(it) })
        }
        is ApiResponse.Empty -> {
          emitAll(loadFromDB().map { Resource.Success(it) })
        }
        is ApiResponse.Error -> {
          onFetchFailed()
          emit(Resource.Error<ResultType>(apiResponse.errorMessage))
        }
      }
    } else {
      emitAll(loadFromDB().map { Resource.Success(it) })
    }
  }

  protected abstract fun onFetchFailed(msg: String? = null)

  protected abstract fun loadFromDB(): Flow<ResultType>

  protected abstract fun shouldFetch(data: ResultType?): Boolean

  protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

  protected abstract suspend fun saveCallResult(data: RequestType)

  fun asFlow(): Flow<Resource<ResultType>> = result
}