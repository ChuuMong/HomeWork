package space.chuumong.homework.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import space.chuumong.data.local.database.AppDatabase
import space.chuumong.data.local.datasource.GithubUserLocalDataSource
import space.chuumong.data.local.datasource.MeetingRoomLocalDataSource
import space.chuumong.data.mapper.GithubUserMapper
import space.chuumong.data.remote.datasource.GithubUserRemoteDataSource
import space.chuumong.data.repositories.GithubUserRepositoryImpl
import space.chuumong.data.repositories.MeetingRoomRepositoryImpl
import space.chuumong.domain.repositories.GithubUserRepository
import space.chuumong.domain.repositories.MeetingRoomRepository

val dataModule = module {
    single {
        AppDatabase.buildDatabase(androidContext())
    }

    factory {
        get<AppDatabase>().likeGithubUserDao()
    }
}

val githubUserdataModule = module {

    factory { GithubUserLocalDataSource(get()) }

    factory { GithubUserRemoteDataSource(get()) }

    factory { GithubUserMapper() }

    factory { GithubUserRepositoryImpl(get(), get(), get()) as GithubUserRepository }
}

val meetingRoomModule = module {
    factory { MeetingRoomLocalDataSource(androidContext()) }

    factory { MeetingRoomRepositoryImpl(get()) as MeetingRoomRepository }
}