package space.chuumong.homework.viewmodel

import androidx.lifecycle.LiveData
import space.chuumong.homework.utils.SingleLiveEvent

class MainViewModel : BaseViewModel() {

    private val _onClickSearchUser = SingleLiveEvent<Any>()
    private val _onClickUI = SingleLiveEvent<Any>()

    val onClickSearchUser: LiveData<Any> get() = _onClickSearchUser
    val onClickUI: LiveData<Any> get() = _onClickUI

    fun onClickSearchUser() {
        _onClickSearchUser.call()
    }

    fun onClickUI() {
        _onClickUI.call()
    }
}