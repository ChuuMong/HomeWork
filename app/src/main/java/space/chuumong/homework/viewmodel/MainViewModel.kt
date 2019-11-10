package space.chuumong.homework.viewmodel

import androidx.lifecycle.LiveData
import space.chuumong.homework.utils.SingleLiveEvent

class MainViewModel : BaseViewModel() {

    private val _onClickGithubUser = SingleLiveEvent<Any>()
    private val _onClickMeetingRoom = SingleLiveEvent<Any>()

    val onClickGithubUser: LiveData<Any> get() = _onClickGithubUser
    val onClickMeetingRoom: LiveData<Any> get() = _onClickMeetingRoom

    fun onClickGithubUser() {
        _onClickGithubUser.call()
    }

    fun onClickMeetingRoom() {
        _onClickMeetingRoom.call()
    }
}