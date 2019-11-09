package space.chuumong.domain.usecase

import io.reactivex.Single
import space.chuumong.domain.entities.GithubSearchUser
import space.chuumong.domain.repositories.GithubUserRepository

class MoreSearchUsers(private val repository: GithubUserRepository) : UseCase<Map<String, Any>, GithubSearchUser>() {

    companion object {
        private const val NAME = "params:name"
        private const val PAGE = "params:page"
    }

    override fun run(params: Map<String, Any>): Single<GithubSearchUser> {
        val name = params[NAME] as? String ?: throw IllegalArgumentException()
        val page = params[PAGE] as? Int ?: throw IllegalArgumentException()

        return repository.moreSearchUsers(name, page)
    }

    fun search(name: String, page: Int): Single<GithubSearchUser> {
        val params = hashMapOf<String, Any>()
        params[NAME] = name
        params[PAGE] = page

        return execute(params)
    }
}