package space.chuumong.data.mapper

import space.chuumong.data.remote.model.response.GithubSearchUserResponse
import space.chuumong.domain.entities.GithubSearchUser
import io.reactivex.functions.Function
import space.chuumong.data.local.entities.LikeGithubUser
import space.chuumong.domain.entities.GithubUser

class GithubUserMapper {

    fun toGithubSearchUserEntity() = Function<GithubSearchUserResponse, GithubSearchUser> {
        it.toEntity()
    }

    fun toGithubLikeUserEntities() = Function<List<LikeGithubUser>, List<GithubUser>> {
        it.map { user ->
            user.toEntity()
        }
    }
}