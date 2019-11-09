package space.chuumong.homework.di

import org.koin.dsl.module
import space.chuumong.homework.viewmodel.MainViewModel
import space.chuumong.homework.viewmodel.SearchUserViewModel


val viewModelModule = module {
    factory { MainViewModel() }

    factory { SearchUserViewModel(get(), get()) }
}
