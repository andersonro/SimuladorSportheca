package br.com.arodevsistemas.simuladorsportheca.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    @SerializedName("nome")
    val name: String,
    @SerializedName("imagem")
    val image: String
): Parcelable