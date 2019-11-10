package space.chuumong.homework.viewmodel

import space.chuumong.data.Result
import space.chuumong.domain.entities.MeetingRoomInfo
import space.chuumong.domain.usecase.GetMeetingRoomInfo
import space.chuumong.domain.usecase.UseCase

class MeetingRoomViewModel(private val getMeetingRoomInfo: GetMeetingRoomInfo) : BaseViewModel() {

    fun getMeetingRoomInfo(result: Result<List<MeetingRoomInfo>>) {
        add(getMeetingRoomInfo.execute(UseCase.None()).subscribe({
            result.onSuccess(it)
        }, {
            result.onFail(it)
        }))
    }
}