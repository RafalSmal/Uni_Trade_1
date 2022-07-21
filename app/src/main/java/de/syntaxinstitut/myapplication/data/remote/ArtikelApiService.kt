package de.syntaxinstitut.myapplication.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "http://syntax-institut.com/public/apps/RafaelSmal/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ArtikelApiService {

    @GET("data.json")
    suspend fun getArtikel(): List<ArtikelData>
}

object ArtikelApi {
    val retrofitService: ArtikelApiService by lazy { retrofit.create(ArtikelApiService::class.java) }
}