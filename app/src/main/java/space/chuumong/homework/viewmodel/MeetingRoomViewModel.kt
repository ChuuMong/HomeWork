package space.chuumong.homework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import space.chuumong.data.Result
import space.chuumong.data.utils.DateUtil
import space.chuumong.domain.entities.MeetingRoomInfo
import space.chuumong.domain.usecase.GetMeetingRoomInfo
import space.chuumong.domain.usecase.UseCase

class MeetingRoomViewModel(private val getMeetingRoomInfo: GetMeetingRoomInfo) : BaseViewModel() {

    private val _todayDate = MutableLiveData<String>()
    private val _isEmptyReservationRoom = MutableLiveData<Boolean>().apply { value = false }
    private val _reservationRoomCount = MutableLiveData<String>()
    private val _reservationRooms = MutableLiveData<List<MeetingRoomInfo>>()

    val todayDate: LiveData<String> get() = _todayDate
    val isEmptyReservationRoom: LiveData<Boolean> get() = _isEmptyReservationRoom
    val reservationRoomCount: LiveData<String> get() = _reservationRoomCount
    val reservationRooms: LiveData<List<MeetingRoomInfo>> get() = _reservationRooms

    fun getMeetingRoomInfo(result: Result<List<MeetingRoomInfo>>) {
        add(getMeetingRoomInfo.execute(UseCase.None()).subscribe({
            _todayDate.value = DateUtil.getTodayDate()

            val canReservationRooms = it.filter { info -> info.canReservation }
            _isEmptyReservationRoom.value = canReservationRooms.isEmpty()

            if (canReservationRooms.isNotEmpty()) {
                _reservationRoomCount.value = canReservationRooms.size.toString()
                _reservationRooms.value = canReservationRooms
            }


            result.onSuccess(it)
        }, {
            result.onFail(it)
        }))
    }
}