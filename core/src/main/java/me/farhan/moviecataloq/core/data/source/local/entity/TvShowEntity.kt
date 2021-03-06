package me.farhan.moviecataloq.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author farhan
 * created at at 11:39 on 05/01/21.
 */
@Entity(tableName = "tv_shows")
@Parcelize
data class TvShowEntity(
  @PrimaryKey
  @SerializedName("id")
  var id: Long,
  @SerializedName("backdrop_path")
  val backdropPath: String,
  @SerializedName("poster_path")
  val posterPath: String,
  @SerializedName("name")
  var name: String,
  @SerializedName("first_air_date")
  var firstAirDate: String,
  @SerializedName("vote_average")
  var voteAverage: Double,
  @SerializedName("vote_count")
  var voteCount: Int,
  @SerializedName("popularity")
  var popularity: Double? = 0.0,
  @SerializedName("overview")
  var overview: String,
  @SerializedName("status")
  var status: String? = "",
  @SerializedName("number_of_episodes")
  var numberOfEpisodes: Int? = 0,
  @SerializedName("number_of_seasons")
  var numberOfSeasons: Int? = 0,
  @SerializedName("tagline")
  var tagline: String? = "-",
  var isFavorite: Int? = 0
) : Parcelable