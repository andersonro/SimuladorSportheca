package br.com.arodevsistemas.simuladorsportheca.data.repositores

import android.os.RemoteException
import br.com.arodevsistemas.simuladorsportheca.data.model.Match
import br.com.arodevsistemas.simuladorsportheca.data.services.ServiceAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MatchRepositoryImpl(private val serviceAPI: ServiceAPI): MatchRepository {
    override suspend fun getMatchList() = flow { 
        try {
            val matchList = serviceAPI.getMatchList()
            emit(matchList)
        }catch (e: HttpException){
            throw RemoteException(e.message() ?: "Erro inesperado no servidor!")
        }
    }
}