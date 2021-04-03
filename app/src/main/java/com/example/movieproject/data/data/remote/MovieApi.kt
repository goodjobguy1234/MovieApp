package com.example.movieproject.data.data.remote

import com.example.movieproject.data.data.entity.MovieData
import com.example.movieproject.extensions.fromJson
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.google.gson.JsonObject


class MovieApi: ApiManager {
    val http = "https://8fc4c056-61b0-4e8b-a053-463874366cce.mock.pstmn.io/get_movie_avaiable"
    override fun getData(callback: (ArrayList<MovieData>) -> Unit){
        http.httpGet().responseString{
            _,_,result->
            val arrayObject = Gson().fromJson<JsonObject>(result.get())
            val arrayMovie = arrayObject.getAsJsonArray("movies")
            val item = Gson().fromJson<ArrayList<MovieData>>(arrayMovie.toString())
            callback(item)
        }
    }
}