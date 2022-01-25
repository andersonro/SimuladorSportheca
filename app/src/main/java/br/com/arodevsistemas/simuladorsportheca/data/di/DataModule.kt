package br.com.arodevsistemas.simuladorsportheca.data.di

import android.util.Log
import br.com.arodevsistemas.simuladorsportheca.data.repositores.MatchRepository
import br.com.arodevsistemas.simuladorsportheca.data.repositores.MatchRepositoryImpl
import br.com.arodevsistemas.simuladorsportheca.data.services.ServiceAPI
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    private const val baseUrl = "https://arodevsistemas.com.br/"
    private const val TAG_OKHTTP = "OkHTTP"

    fun load() {
        loadKoinModules(networkModules() + repositoriesModule())
    }

    private fun networkModules(): Module {
        return module {
            single {

                val interceptor = HttpLoggingInterceptor {
                    Log.e(TAG_OKHTTP, "networkModules $it")
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<ServiceAPI>(get(), get())
            }
        }
    }

    private fun repositoriesModule(): Module{
        return  module {
            single<MatchRepository> {
                MatchRepositoryImpl(get())
            }
        }
    }

    private inline fun <reified T> createService(client: OkHttpClient,
                                                 factory: GsonConverterFactory): T {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(T::class.java)
    }

}