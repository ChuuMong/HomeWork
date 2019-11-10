package space.chuumong.homework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import space.chuumong.data.Result
import space.chuumong.domain.entities.GithubUser
import space.chuumong.domain.usecase.DeleteLikeUser
import space.chuumong.domain.usecase.GetAllLikeUsers
import space.chuumong.domain.usecase.SaveLikeUser
import space.chuumong.domain.usecase.UseCase

class LikeUserViewModel(
    private val getAllLikeUsers: GetAllLikeUsers,
    private val saveLikeUser: SaveLikeUser,
    private val deleteLikeUser: DeleteLikeUser
) : BaseViewModel() {

    private val _isResultEmpty = MutableLiveData<Boolean>().apply { value = false }

    val isResultEmpty: LiveData<Boolean> get() = _isResultEmpty

    fun getAllLikeUsers(result: Result<List<GithubUser>>) {
        add(getAllLikeUsers.execute(UseCase.None()).subscribe({
            _isResultEmpty.value = it.isEmpty()
            
            result.onSuccess(it)
        }, {
            result.onFail(it)
        }))
    }

    fun saveLikeUser(user: GithubUser, result: Result<UseCase.None>) {
        add(saveLikeUser.save(user).subscribe({
            result.onSuccess(UseCase.None())
        }, {
            result.onFail(it)
        }))
    }

    fun deleteLikeUser(user: GithubUser, result: Result<UseCase.None>) {
        add(deleteLikeUser.delete(user).subscribe({
            result.onSuccess(UseCase.None())
        }, {
            result.onFail(it)
        }))
    }
}