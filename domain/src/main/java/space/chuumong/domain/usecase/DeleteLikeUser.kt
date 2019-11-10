package space.chuumong.domain.usecase

import io.reactivex.Completable
import space.chuumong.domain.entities.GithubUser
import space.chuumong.domain.repositories.GithubUserRepository


/**
 * Created by Home on 2019-11-09.
 */
class DeleteLikeUser(private val repository: GithubUserRepository): CompletableUseCase<GithubUser>() {

    override fun run(params: GithubUser): Completable {
        return repository.deleteLikeUser(params)
    }

    fun delete(user: GithubUser): Completable {
        return execute(user)
    }
}