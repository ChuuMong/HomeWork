package space.chuumong.data.remote.datasource

import io.reactivex.Single
import space.chuumong.data.remote.api.ApiService
import space.chuumong.data.remote.model.response.GithubSearchUserResponse

class GithubUserRemoteDataSource(private val apiService: ApiService) {

    fun searchUsers(name: String): Single<GithubSearchUserResponse> {
        return apiService.githubSearchUsers(name)
    }

    fun moreSearchUsers(name: String, page: Int): Single<GithubSearchUserResponse> {
        return apiService.githubSearchUsers(name, page)
    }
}