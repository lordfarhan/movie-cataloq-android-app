package me.farhan.moviecataloq.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author farhan
 * created at at 9:01 on 23/10/2020.
 */
@Parcelize
data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val cover: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String,
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
        return releaseDate.split("-")[0]
    }
}