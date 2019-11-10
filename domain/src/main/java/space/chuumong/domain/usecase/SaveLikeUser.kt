package space.chuumong.domain.usecase

import io.reactivex.Completable
import io.reactivex.Single
import space.chuumong.domain.entities.GithubUser
import space.chuumong.domain.repositories.GithubUserRepository


/**
 * Created by Home on 2019-11-09.
 */
class SaveLikeUser(private val repository: GithubUserRepository) :
    CompletableUseCase<GithubUser>() {

    override fun run(params: GithubUser): Completable {
        return repository.saveLikeUser(params)
    }

    fun save(githubUser: GithubUser): Completable {
        return execute(githubUser)
    }
}