package br.com.arodevsistemas.simuladorsportheca.domain.di

import br.com.arodevsistemas.simuladorsportheca.domain.MatchUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory {
                MatchUseCase(get())
            }
        }
    }
}