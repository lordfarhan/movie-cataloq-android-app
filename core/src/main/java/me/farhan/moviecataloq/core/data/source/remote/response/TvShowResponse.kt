package me.farhan.moviecataloq.core.data.source.remote.response

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author farhan
 * created at at 12:03 on 05/01/21.
 */
@Parcelize
data class TvShowResponse(
  @field:SerializedName("id")
  val id: Long,
  @field:SerializedName("poster_path")
  val cover: String,
  @field:SerializedName("name")
  val name: String,
  @field:SerializedName("first_air_date")
  val firstAirDate: String,
  @field:SerializedName("vote_average")
  val voteAverage: Double,
  @field:SerializedName("vote_count")
  val voteCount: Int,
  @field:SerializedName("popularity")
  val popularity: Double? = 0.0,
  @field:SerializedName("overview")
  val overview: String,
  @field:SerializedName("status")
  val status: String? = "",
  @field:SerializedName("number_of_episodes")
  val numberOfEpisodes: Int? = 0,
  @field:SerializedName("number_of_seasons")
  val numberOfSeasons: Int? = 0,
  @field:SerializedName("tagline")
  val tagline: String? = "-",
  val isFavorite: Int? = 0
) : Parcelable