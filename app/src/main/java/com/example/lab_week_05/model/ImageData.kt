package com.example.lab_week_05.model
import com.squareup.moshi.Json

data class ImageData(
    @field:Json(name = "url") val imageUrl: String,
    val breeds: List<CatBreedData>
)
//in moshi, they automatically map the Json to the variable that have the same name

//@field:Json(name = "url") is used to tell moshi what "the variable" is supposed to be mapped with.
//(in this case, imageUrl contains data from JSON.url)