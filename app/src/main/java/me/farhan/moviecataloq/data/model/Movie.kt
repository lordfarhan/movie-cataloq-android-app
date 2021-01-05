package me.farhan.moviecataloq.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import me.farhan.moviecataloq.util.getMonthFromInt

/**
 * @author farhan
 * created at at 9:01 on 23/10/2020.
 */
@Entity(tableName = "movies")
@Parcelize
data class Movie(
  @PrimaryKey
  @SerializedName("id")
  val id: Long,
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
  val popularity: Double? = 0.0,
  @SerializedName("overview")
  val overview: String,
  @SerializedName("status")
  val status: String? = "",
  @SerializedName("budget")
  val budget: Long? = 0,
  @SerializedName("revenue")
  val revenue: Long? = 0,
  @SerializedName("tagline")
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