package br.com.arodevsistemas.simuladorsportheca.data.services

import br.com.arodevsistemas.simuladorsportheca.data.model.Match
import retrofit2.http.GET

interface ServiceAPI {

    @GET("sportheca-simulate-api")
    suspend fun getMatchList(): List<Match>
}