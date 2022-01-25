package br.com.arodevsistemas.simuladorsportheca.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    @SerializedName("nome")
    val name: String,
    @SerializedName("imagem")
    val image: String,
    @SerializedName("forca")
    val star: Float = 0f,
    var score: Int = 0
): Parcelable
