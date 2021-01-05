package me.farhan.moviecataloq.util

import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.model.TvShow

/**
 * @author farhan
 * created at at 10:34 on 23/10/2020.
 */
class DataDummy {
    companion object {
        fun getMovies(): List<Movie> {
            val movies: ArrayList<Movie> = ArrayList()
            movies.add(
                Movie(
                    1,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            movies.add(
                Movie(
                    2,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            movies.add(
                Movie(
                    3,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            movies.add(
                Movie(
                    4,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            movies.add(
                Movie(
                    5,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            movies.add(
                Movie(
                    6,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            movies.add(
                Movie(
                    7,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            movies.add(
                Movie(
                    8,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            movies.add(
                Movie(
                    9,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            movies.add(
                Movie(
                    10,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            return movies
        }

        fun getMovieById(id: Long): Movie {
            var movie: Movie? = null
            getMovies().forEach {
                if (it.id == id) movie = it
            }
            return movie as Movie
        }

        fun getTvShows(): List<TvShow> {
            val tvShows: ArrayList<TvShow> = ArrayList()
            tvShows.add(
                TvShow(
                    1,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            tvShows.add(
                TvShow(
                    2,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            tvShows.add(
                TvShow(
                    3,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            tvShows.add(
                TvShow(
                    4,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            tvShows.add(
                TvShow(
                    5,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            tvShows.add(
                TvShow(
                    6,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            tvShows.add(
                TvShow(
                    7,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            tvShows.add(
                TvShow(
                    8,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            tvShows.add(
                TvShow(
                    9,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            tvShows.add(
                TvShow(
                    10,
                    "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                    "Peninsula",
                    "12 2 2020",
                    71.0,
                    641,
                    12020.0,
                    "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                )
            )
            return tvShows.reversed()
        }

        fun getTvShowById(id: Long): TvShow {
            var tvShow: TvShow? = null
            getTvShows().forEach {
                if (it.id == id) tvShow = it
            }
            return tvShow as TvShow
        }
    }
}