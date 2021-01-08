package me.farhan.moviecataloq.core.util

import me.farhan.moviecataloq.core.data.source.local.entity.MovieEntity
import me.farhan.moviecataloq.core.data.source.local.entity.TvShowEntity
import me.farhan.moviecataloq.core.data.source.remote.response.MovieResponse
import me.farhan.moviecataloq.core.data.source.remote.response.TvShowResponse
import me.farhan.moviecataloq.core.domain.model.Movie
import me.farhan.moviecataloq.core.domain.model.TvShow

/**
 * @author farhan
 * created at at 10:53 on 05/01/21.
 */
object DataMapper {
  fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
    val list = ArrayList<MovieEntity>()
    input.map {
      val movie = MovieEntity(
        id = it.id,
        cover = it.cover,
        title = it.title,
        releaseDate = it.releaseDate,
        voteAverage = it.voteAverage,
        voteCount = it.voteCount,
        popularity = it.popularity,
        overview = it.overview,
        status = it.status,
        budget = it.budget,
        revenue = it.revenue,
        tagline = it.tagline,
        isFavorite = it.isFavorite
      )
      list.add(movie)
    }
    return list
  }

  fun mapMovieResponseToEntity(input: MovieResponse): MovieEntity {
    return MovieEntity(
      id = input.id,
      cover = input.cover,
      title = input.title,
      releaseDate = input.releaseDate,
      voteAverage = input.voteAverage,
      voteCount = input.voteCount,
      popularity = input.popularity,
      overview = input.overview,
      status = input.status,
      budget = input.budget,
      revenue = input.revenue,
      tagline = input.tagline,
      isFavorite = input.isFavorite
    )
  }

  fun mapTvShowResponsesToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
    val list = ArrayList<TvShowEntity>()
    input.map {
      val tvShow = TvShowEntity(
        id = it.id,
        cover = it.cover,
        name = it.name,
        firstAirDate = it.firstAirDate,
        voteAverage = it.voteAverage,
        voteCount = it.voteCount,
        popularity = it.popularity,
        overview = it.overview,
        status = it.status,
        numberOfEpisodes = it.numberOfEpisodes,
        numberOfSeasons = it.numberOfSeasons,
        tagline = it.tagline,
        isFavorite = it.isFavorite
      )
      list.add(tvShow)
    }
    return list
  }

  fun mapTvShowResponseToEntity(input: TvShowResponse): TvShowEntity {
    return TvShowEntity(
      id = input.id,
      cover = input.cover,
      name = input.name,
      firstAirDate = input.firstAirDate,
      voteAverage = input.voteAverage,
      voteCount = input.voteCount,
      popularity = input.popularity,
      overview = input.overview,
      status = input.status,
      numberOfEpisodes = input.numberOfEpisodes,
      numberOfSeasons = input.numberOfSeasons,
      tagline = input.tagline,
      isFavorite = input.isFavorite
    )
  }

  fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
    input.map {
      Movie(
        id = it.id,
        cover = it.cover,
        title = it.title,
        releaseDate = it.releaseDate,
        voteAverage = it.voteAverage,
        voteCount = it.voteCount,
        popularity = it.popularity,
        overview = it.overview,
        status = it.status,
        budget = it.budget,
        revenue = it.revenue,
        tagline = it.tagline,
        isFavorite = it.isFavorite
      )
    }

  fun mapMovieEntityToDomain(input: MovieEntity): Movie =
    Movie(
      id = input.id,
      cover = input.cover,
      title = input.title,
      releaseDate = input.releaseDate,
      voteAverage = input.voteAverage,
      voteCount = input.voteCount,
      popularity = input.popularity,
      overview = input.overview,
      status = input.status,
      budget = input.budget,
      revenue = input.revenue,
      tagline = input.tagline,
      isFavorite = input.isFavorite
    )

  fun mapMovieDomainToEntity(input: Movie) = MovieEntity(
    id = input.id,
    cover = input.cover,
    title = input.title,
    releaseDate = input.releaseDate,
    voteAverage = input.voteAverage,
    voteCount = input.voteCount,
    popularity = input.popularity,
    overview = input.overview,
    status = input.status,
    budget = input.budget,
    revenue = input.revenue,
    tagline = input.tagline,
    isFavorite = input.isFavorite
  )

  fun mapTvShowEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
    input.map {
      TvShow(
        id = it.id,
        cover = it.cover,
        name = it.name,
        firstAirDate = it.firstAirDate,
        voteAverage = it.voteAverage,
        voteCount = it.voteCount,
        popularity = it.popularity,
        overview = it.overview,
        status = it.status,
        numberOfEpisodes = it.numberOfEpisodes,
        numberOfSeasons = it.numberOfSeasons,
        tagline = it.tagline,
        isFavorite = it.isFavorite
      )
    }

  fun mapTvShowEntityToDomain(input: TvShowEntity): TvShow =
    TvShow(
      id = input.id,
      cover = input.cover,
      name = input.name,
      firstAirDate = input.firstAirDate,
      voteAverage = input.voteAverage,
      voteCount = input.voteCount,
      popularity = input.popularity,
      overview = input.overview,
      status = input.status,
      numberOfEpisodes = input.numberOfEpisodes,
      numberOfSeasons = input.numberOfSeasons,
      tagline = input.tagline,
      isFavorite = input.isFavorite
    )

  fun mapTvShowDomainToEntity(input: TvShow) = TvShowEntity(
    id = input.id,
    cover = input.cover,
    name = input.name,
    firstAirDate = input.firstAirDate,
    voteAverage = input.voteAverage,
    voteCount = input.voteCount,
    popularity = input.popularity,
    overview = input.overview,
    status = input.status,
    numberOfEpisodes = input.numberOfEpisodes,
    numberOfSeasons = input.numberOfSeasons,
    tagline = input.tagline,
    isFavorite = input.isFavorite
  )
}