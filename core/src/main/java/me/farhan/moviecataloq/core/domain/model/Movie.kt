package me.farhan.moviecataloq.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author farhan
 * created at at 9:01 on 23/10/2020.
 */
@Parcelize
data class Movie(
  val id: Long,
  val cover: String,
  val title: String,
  val releaseDate: String,
  val voteAverage: Double,
  val voteCount: Int,
  val popularity: Double? = 0.0,
  val overview: String,
  val status: String? = "",
  val budget: Long? = 0,
  val revenue: Long? = 0,
  val tagline: String? = "-",
  val isFavorite: Int? = 0
) : Parcelable {
  fun getYear(): String = releaseDate.split("-")[0]

  fun getDate(): String {
    val dateString = releaseDate.split("-")
    val date = dateString[2]
    val month = dateString[1]
    val year = dateString[0]
    return "$date $month $year"
  }
}