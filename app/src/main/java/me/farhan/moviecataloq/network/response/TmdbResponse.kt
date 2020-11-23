package me.farhan.moviecataloq.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author farhan
 * created at at 15:21 on 21/11/20.
 */
data class TmdbResponse<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Long,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("results")
    val results: List<T> = emptyList()
)