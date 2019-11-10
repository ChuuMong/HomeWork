package space.chuumong.domain.repositories

import io.reactivex.Completable
import io.reactivex.Single
import space.chuumong.domain.entities.GithubSearchUser
import space.chuumong.domain.entities.GithubUser
import space.chuumong.domain.usecase.UseCase


/**
 * Created by Home on 2019-11-09.
 */
interface GithubUserRepository {

    fun searchUsers(name: String): Single<GithubSearchUser>

    fun moreSearchUsers(name: String, page: Int): Single<GithubSearchUser>

    fun saveLikeUser(user: GithubUser): Completable

    fun getAllLikeUsers(): Single<List<GithubUser>>

    fun deleteLikeUser(user: GithubUser): Completable
}