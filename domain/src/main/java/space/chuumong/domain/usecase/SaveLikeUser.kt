package space.chuumong.domain.usecase

import io.reactivex.Completable
import space.chuumong.domain.entities.GithubUser
import space.chuumong.domain.repositories.GithubUserRepository

class SaveLikeUser(private val repository: GithubUserRepository) :
    CompletableUseCase<GithubUser>() {

    override fun run(params: GithubUser): Completable {
        return repository.saveLikeUser(params)
    }

    fun save(githubUser: GithubUser): Completable {
        return execute(githubUser)
    }
}