package id.aasumitro.dicodingbeginner.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */

data class ResultHeadline(
        @SerializedName("status") var status: String = "",
        @SerializedName("totalResults") var total: String = "",
        @SerializedName("code") var code: String? = null,
        @SerializedName("message") var message: String? = null,
        @SerializedName("articles") var articles: List<Headline> = emptyList())

data class ResultEverything(
        @SerializedName("status") var status: String = "",
        @SerializedName("totalResults") var total: String = "",
        @SerializedName("code") var code: String? = null,
        @SerializedName("message") var message: String? = null,
        @SerializedName("articles") var articles: List<Everything> = emptyList())


