package space.chuumong.data.remote.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import space.chuumong.data.remote.model.response.GithubSearchUserResponse


/**
 * Created by Home on 2019-11-09.
 */
interface ApiService {

    @GET("search/users")
    fun githubSearchUsers(@Query("q") name: String, @Query("page") page: Int = 1, @Query("per_page") perPage: Int = 20): Single<GithubSearchUserResponse>
}