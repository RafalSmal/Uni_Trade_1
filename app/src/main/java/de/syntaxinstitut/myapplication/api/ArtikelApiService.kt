package de.syntaxinstitut.myapplication.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// Die Konstante enthält die URL der API
const val BASE_URL = "http://syntax-institut.com/public/apps/RafaelSmal/"
val client: OkHttpClient = OkHttpClient.Builder().addInterceptor{
    chain ->  val newRequest : Request = chain.request().newBuilder().build()
    chain.proceed(newRequest)
}.build()

// Moshi konvertiert Serverantworten in Kotlin Objekte
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Retrofit übernimmt die Kommunikation und übersetzt die Antwort
private val retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// Das Interface bestimmt, wie mit dem Server kommuniziert wird
interface ArtikelApiService {

    @GET("data.json")
    suspend fun getArtikel(): List<ArtikelData>
}

// Das Objekt dient als Zugangspunkt für den Rest der App und stellt den API Service zur Verfügung
object ArtikelApi {
    val retrofitService: ArtikelApiService by lazy { retrofit.create(ArtikelApiService::class.java) }
}