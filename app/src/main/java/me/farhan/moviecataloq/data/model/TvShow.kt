package me.farhan.moviecataloq.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author farhan
 * created at at 13:19 on 27/10/2020.
 */
@Parcelize
data class TvShow(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val cover: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("overview")
    val overview: String,
) : Parcelable {
    fun getVoteAverage(): Float {
        return if (!voteAverage.isNaN()) {
            voteAverage.toFloat() / 2
        } else {
            0f
        }
    }

    fun getYear(): String {
        return firstAirDate.split("-")[0]
    }
}