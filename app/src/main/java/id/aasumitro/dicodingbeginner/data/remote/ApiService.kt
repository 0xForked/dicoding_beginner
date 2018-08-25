package id.aasumitro.dicodingbeginner.data.remote

import id.aasumitro.dicodingbeginner.data.model.ResultEverything
import id.aasumitro.dicodingbeginner.data.model.ResultHeadline
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */

interface ApiService {

    @GET("top-headlines")
    fun headlinesNews(
            @Query("sources") sources: String,
            @Query("apiKey") key: String
    ): Observable<ResultHeadline>

    @GET("everything")
    fun everythingNews(
            @Query("sources") sources: String,
            @Query("apiKey") key: String
    ): Observable<ResultEverything>

}
