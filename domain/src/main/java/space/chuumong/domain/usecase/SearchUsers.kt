package space.chuumong.domain.usecase

import io.reactivex.Single
import space.chuumong.domain.entities.GithubSearchUser
import space.chuumong.domain.repositories.GithubUserRepository


class SearchUsers(private val githubUserRepository: GithubUserRepository) :
    UseCase<Map<String, String>, GithubSearchUser>() {

    companion object {
        private const val NAME = "params:name"
    }

    override fun run(params: Map<String, String>): Single<GithubSearchUser> {
        val name = params[NAME] ?: throw NullPointerException()

        return githubUserRepository.searchUsers(name)
    }

    fun search(name: String): Single<GithubSearchUser> {
        val params = hashMapOf<String, String>()
        params[NAME] = name

        return execute(params)
    }
}