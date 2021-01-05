package me.farhan.moviecataloq.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import me.farhan.moviecataloq.util.getMonthFromInt

/**
 * @author farhan
 * created at at 13:19 on 27/10/2020.
 */
@Entity(tableName = "tv_shows")
@Parcelize
data class TvShow(
  @PrimaryKey
  @SerializedName("id")
  val id: Long,
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
  val popularity: Double? = 0.0,
  @SerializedName("overview")
  val overview: String,
  @SerializedName("status")
  val status: String? = "",
  @SerializedName("number_of_episodes")
  val numberOfEpisodes: Int? = 0,
  @SerializedName("number_of_seasons")
  val numberOfSeasons: Int? = 0,
  @SerializedName("tagline")
  val tagline: String? = "-",
  val isFavorite: Int? = 0
) : Parcelable {
  fun getYear(): String {
    return firstAirDate.split("-")[0]
  }

  fun getDate(): String {
    val dateString = firstAirDate.split("-")
    val date = dateString[2]
    val month = getMonthFromInt(dateString[1].toInt())
    val year = getYear()
    return "$date $month $year"
  }
}