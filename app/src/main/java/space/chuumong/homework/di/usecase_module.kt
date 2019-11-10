package space.chuumong.homework.di

import org.koin.dsl.module
import space.chuumong.domain.usecase.*

val useCaseModule = module {
    factory { SearchUsers(get()) }

    factory { MoreSearchUsers(get()) }

    factory { GetAllLikeUsers(get()) }

    factory { SaveLikeUser(get()) }
    
    factory { DeleteLikeUser(get()) }

    factory { GetMeetingRoomInfo(get()) }
}