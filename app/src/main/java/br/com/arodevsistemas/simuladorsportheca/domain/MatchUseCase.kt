package br.com.arodevsistemas.simuladorsportheca.domain

import br.com.arodevsistemas.simuladorsportheca.data.model.Match
import br.com.arodevsistemas.simuladorsportheca.data.repositores.MatchRepository
import kotlinx.coroutines.flow.Flow

class MatchUseCase(private val matchRepository: MatchRepository) {
    suspend fun getMatchList(): Flow<List<Match>>{
        return matchRepository.getMatchList()
    }
}
