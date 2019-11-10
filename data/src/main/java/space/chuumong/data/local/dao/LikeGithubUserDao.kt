package space.chuumong.data.local.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import space.chuumong.data.local.entities.LikeGithubUser


@Dao
interface LikeGithubUserDao {

    @Query("SELECT * FROM like_github_user")
    fun getAllUsers(): Single<List<LikeGithubUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: LikeGithubUser): Completable

    @Delete
    fun delete(user: LikeGithubUser): Completable
}