package br.com.arodevsistemas.simuladorsportheca.data.repositores

import br.com.arodevsistemas.simuladorsportheca.data.model.Match
import kotlinx.coroutines.flow.Flow

interface MatchRepository {

    suspend fun getMatchList(): Flow<List<Match>>
}