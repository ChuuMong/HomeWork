package space.chuumong.homework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import space.chuumong.data.Result
import space.chuumong.domain.entities.GithubSearchUser
import space.chuumong.domain.usecase.MoreSearchUsers
import space.chuumong.domain.usecase.SearchUsers

class SearchUserViewModel(
    private val searchUser: SearchUsers,
    private val moreSearchUsers: MoreSearchUsers
) : BaseViewModel() {

    private val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    private val _isResultEmpty = MutableLiveData<Boolean>().apply { value = false }

    val isLoading: LiveData<Boolean> get() = _isLoading
    val isResultEmpty: LiveData<Boolean> get() = _isResultEmpty

    fun searchUsers(name: String, result: Result<GithubSearchUser>) {
        _isResultEmpty.value = false
        _isLoading.value = true
        add(searchUser.search(name).subscribe({
            _isResultEmpty.value = it.users.isEmpty()
            _isLoading.value = false

            result.onSuccess(it)
        }, {
            result.onFail(it)
        }))
    }

    fun moreSearchUsers(name: String, searchPage: Int, result: Result<GithubSearchUser>) {
        add(moreSearchUsers.search(name, searchPage).subscribe({
            result.onSuccess(it)
        }, {
            result.onFail(it)
        }))
    }
}