package id.aasumitro.dicodingbeginner.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */

data class Headline(
        @SerializedName("author") var author: String? = null,
        @SerializedName("title") var title: String = "",
        @SerializedName("description") var description: String = "",
        @SerializedName("url") var url: String = "",
        @SerializedName("urlToImage") var urlToImage: String? = null,
        @SerializedName("publishedAt") var publishedAt: String? = null)

data class Everything(
        @SerializedName("author") var author: String? = null,
        @SerializedName("title") var title: String = "",
        @SerializedName("description") var description: String = "",
        @SerializedName("url") var url: String = "",
        @SerializedName("urlToImage") var urlToImage: String? = null,
        @SerializedName("publishedAt") var publishedAt: String? = null)