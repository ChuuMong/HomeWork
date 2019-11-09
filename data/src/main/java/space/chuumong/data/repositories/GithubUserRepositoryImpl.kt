package space.chuumong.data.repositories

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import space.chuumong.data.local.datasource.GithubUserLocalDataSource
import space.chuumong.data.mapper.GithubUserMapper
import space.chuumong.data.remote.datasource.GithubUserRemoteDataSource
import space.chuumong.domain.entities.GithubSearchUser
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
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun moreSearchUsers(name: String, page: Int): Single<GithubSearchUser> {
        return remoteDataSource.moreSearchUsers(name, page)
            .map(mapper.toGithubSearchUserEntity())
            .observeOn(AndroidSchedulers.mainThread())
    }
}