package space.chuumong.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import space.chuumong.domain.entities.GithubUser


@Entity(tableName = "like_github_user")
data class LikeGithubUser(
    @PrimaryKey
    val name: String,
    val profileImage: String,
    val score: Float
) {
    fun toEntity() = GithubUser(name, profileImage, score, true)
}