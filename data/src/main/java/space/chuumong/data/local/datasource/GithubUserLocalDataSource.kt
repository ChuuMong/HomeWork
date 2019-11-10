package space.chuumong.data.local.datasource

import io.reactivex.Completable
import io.reactivex.Single
import space.chuumong.data.local.dao.LikeGithubUserDao
import space.chuumong.data.local.entities.LikeGithubUser
import space.chuumong.domain.entities.GithubUser
import space.chuumong.domain.usecase.UseCase


/**
 * Created by Home on 2019-11-09.
 */
class GithubUserLocalDataSource(private val likeGithubUserDao: LikeGithubUserDao) {
    fun saveLikeUser(user: GithubUser): Completable {
        return likeGithubUserDao.insert(LikeGithubUser(user.name, user.profileImage, user.score))
    }

    fun getAllLikeUsers(): Single<List<LikeGithubUser>> {
        return likeGithubUserDao.getAllUsers()
    }

    fun deleteLikeUser(user: GithubUser): Completable {
        return likeGithubUserDao.delete(LikeGithubUser(user.name, user.profileImage, user.score))
    }
}