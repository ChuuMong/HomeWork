package space.chuumong.homework.di

import org.koin.dsl.module
import space.chuumong.homework.viewmodel.MainViewModel


val viewModelModule = module {
    factory { MainViewModel() }
}
