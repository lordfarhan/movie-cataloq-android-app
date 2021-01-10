package me.farhan.moviecataloq.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author farhan
 * created at at 12:01 on 05/01/21.
 */
@Parcelize
data class MovieResponse(
  @field:SerializedName("id")
  val id: Long,
  @field:SerializedName("backdrop_path")
  val backdropPath: String,
  @field:SerializedName("poster_path")
  val posterPath: String,
  @field:SerializedName("title")
  val title: String,
  @field:SerializedName("release_date")
  val releaseDate: String,
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
  @field:SerializedName("budget")
  val budget: Long? = 0,
  @field:SerializedName("revenue")
  val revenue: Long? = 0,
  @field:SerializedName("tagline")
  val tagline: String? = "-",
  val isFavorite: Int? = 0
) : Parcelable