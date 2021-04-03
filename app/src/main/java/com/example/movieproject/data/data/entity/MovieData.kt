package com.example.movieproject.data.data.entity

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONObject


data class MovieData(

  val title_en:String,
  val title_th:String,
  val duration:Int,
  @SerializedName("poster_url")
  val url:String
)
