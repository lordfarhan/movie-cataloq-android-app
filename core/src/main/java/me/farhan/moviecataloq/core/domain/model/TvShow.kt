package me.farhan.moviecataloq.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author farhan
 * created at at 13:19 on 27/10/2020.
 */
@Parcelize
data class TvShow(
  val id: Long,
  val backdropPath: String,
  val posterPath: String,
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
  fun getYear(): String = firstAirDate.split("-")[0]

  fun getDate(): String {
    val dateString = firstAirDate.split("-")
    val date = dateString[2]
    val month = dateString[1]
    val year = dateString[0]
    return "$date $month $year"
  }
}