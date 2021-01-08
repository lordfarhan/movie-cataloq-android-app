package me.farhan.moviecataloq.core.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import me.farhan.moviecataloq.core.util.getMonthFromInt

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
    val month = getMonthFromInt(dateString[1].toInt())
    val year = getYear()
    return "$date $month $year"
  }
}