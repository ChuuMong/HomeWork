package space.chuumong.data.repositories

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import space.chuumong.data.local.datasource.MeetingRoomLocalDataSource
import space.chuumong.domain.entities.MeetingRoomInfo
import space.chuumong.domain.repositories.MeetingRoomRepository

class MeetingRoomRepositoryImpl(private val localDataSource: MeetingRoomLocalDataSource) :
    MeetingRoomRepository {
    
    override fun getMeetingRoomInfo(): Single<List<MeetingRoomInfo>> {
        return localDataSource.getMeetingRoomInfos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}