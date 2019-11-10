package space.chuumong.domain.usecase

import io.reactivex.Single
import space.chuumong.domain.entities.GithubUser
import space.chuumong.domain.repositories.GithubUserRepository


class GetAllLikeUsers(private val repository: GithubUserRepository): UseCase<UseCase.None, List<GithubUser>>() {

    override fun run(params: None): Single<List<GithubUser>> {
        return repository.getAllLikeUsers()
    }
}