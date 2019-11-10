package space.chuumong.data.repositories

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import space.chuumong.data.local.datasource.GithubUserLocalDataSource
import space.chuumong.data.local.entities.LikeGithubUser
import space.chuumong.data.mapper.GithubUserMapper
import space.chuumong.data.remote.datasource.GithubUserRemoteDataSource
import space.chuumong.domain.entities.GithubSearchUser
import space.chuumong.domain.entities.GithubUser
import space.chuumong.domain.repositories.GithubUserRepository
import space.chuumong.domain.usecase.UseCase


class GithubUserRepositoryImpl(
    private val localDataSource: GithubUserLocalDataSource,
    private val remoteDataSource: GithubUserRemoteDataSource,
    private val mapper: GithubUserMapper
) : GithubUserRepository {

    override fun searchUsers(name: String): Single<GithubSearchUser> {
        return remoteDataSource.searchUsers(name)
            .map(mapper.toGithubSearchUserEntity())
            .zipWith(getAllLikeUsers(), setSearchLikeUsers())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun moreSearchUsers(name: String, page: Int): Single<GithubSearchUser> {
        return remoteDataSource.moreSearchUsers(name, page)
            .map(mapper.toGithubSearchUserEntity())
            .zipWith(getAllLikeUsers(), setSearchLikeUsers())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun setSearchLikeUsers() =
        BiFunction<GithubSearchUser, List<GithubUser>, GithubSearchUser> { searchUsers, likeUsers ->
            likeUsers.forEach { likeUser ->
                searchUsers.users.find { likeUser.name == it.name }?.isLike = true
            }

            searchUsers
        }

    override fun getAllLikeUsers(): Single<List<GithubUser>> {
        return localDataSource.getAllLikeUsers()
            .map(mapper.toGithubLikeUserEntities())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun saveLikeUser(user: GithubUser): Completable {
        return localDataSource.saveLikeUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteLikeUser(user: GithubUser): Completable {
        return localDataSource.deleteLikeUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}