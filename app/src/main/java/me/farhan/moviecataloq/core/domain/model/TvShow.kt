package me.farhan.moviecataloq.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import me.farhan.moviecataloq.core.util.getMonthFromInt

/**
 * @author farhan
 * created at at 13:19 on 27/10/2020.
 */
@Parcelize
data class TvShow(
  val id: Long,
  val cover: String,
  val name: String,
  val firstAirDate: String,
  val voteAverage: Double,
  val voteCount: Int,
  val popularity: Double? = 0.0,
  val overview: String,
  val status: String? = "",
  val numberOfEpisodes: Int? = 0,
  val numberOfSeasons: Int? = 0,
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