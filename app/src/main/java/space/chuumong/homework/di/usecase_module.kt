package space.chuumong.homework.di

import org.koin.dsl.module
import space.chuumong.domain.usecase.MoreSearchUsers
import space.chuumong.domain.usecase.SearchUsers

val useCaseModule = module {
    factory { SearchUsers(get()) }

    factory { MoreSearchUsers(get()) }
}