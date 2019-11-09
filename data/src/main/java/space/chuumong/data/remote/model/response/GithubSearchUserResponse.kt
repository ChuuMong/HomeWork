package space.chuumong.data.remote.model.response

import com.google.gson.annotations.SerializedName
import space.chuumong.domain.entities.GithubSearchUser
import space.chuumong.domain.entities.GithubUser

data class GithubSearchUserResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("items")
    val users: List<GithubUserResponse>
) {
    fun toEntity(): GithubSearchUser {
        val users = mutableListOf<GithubUser>()
        this.users.forEach { users.add(it.toEntity()) }

        return GithubSearchUser(totalCount, users)
    }
}

data class GithubUserResponse(
    @SerializedName("login")
    val name: String,
    @SerializedName("avatar_url")
    val profileImage: String,
    @SerializedName("score")
    val score: Float
) {
    fun toEntity() = GithubUser(name, profileImage, score)
}