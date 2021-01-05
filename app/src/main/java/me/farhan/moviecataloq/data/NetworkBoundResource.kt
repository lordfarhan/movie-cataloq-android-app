package me.farhan.moviecataloq.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import me.farhan.moviecataloq.network.response.ApiResponse
import me.farhan.moviecataloq.network.response.StatusResponse
import me.farhan.moviecataloq.util.AppExecutors
import me.farhan.moviecataloq.vo.Resource

/**
 * @author farhan
 * created at at 12:07 on 28/11/20.
 */
abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {

  private val result = MediatorLiveData<Resource<ResultType>>()

  init {
    result.value = Resource.loading(null)

    @Suppress("LeakingThis")
    val dbSource = loadFromDB()

    result.addSource(dbSource) { data ->
      result.removeSource(dbSource)
      if (shouldFetch(data)) {
        fetchFromNetwork(dbSource)
      } else {
        result.addSource(dbSource) { newData ->
          result.value = Resource.success(newData)
        }
      }
    }
  }

  protected abstract fun onFetchFailed(msg: String?)

  protected abstract fun loadFromDB(): LiveData<ResultType>

  protected abstract fun shouldFetch(data: ResultType?): Boolean

  protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

  protected abstract fun saveCallResult(data: RequestType)

  private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {

    val apiResponse = createCall()

    result.addSource(dbSource) { newData ->
      result.value = Resource.loading(newData)
    }
    result.addSource(apiResponse) { response ->
      result.removeSource(apiResponse)
      result.removeSource(dbSource)
      when (response.status) {
        StatusResponse.SUCCESS ->
          mExecutors.diskIO().execute {
            response.body?.let {
              saveCallResult(it)
            }
            mExecutors.mainThread().execute {
              result.addSource(loadFromDB()) { newData ->
                result.value = Resource.success(newData)
              }
            }
          }
        StatusResponse.EMPTY -> mExecutors.mainThread().execute {
          result.addSource(loadFromDB()) { newData ->
            result.value = Resource.success(newData)
          }
        }
        StatusResponse.ERROR -> {
          onFetchFailed(response.message)
          result.addSource(dbSource) { newData ->
            result.value = Resource.error(response.message, newData)
          }
        }
      }
    }
  }

  fun asLiveData(): LiveData<Resource<ResultType>> = result
}