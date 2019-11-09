package space.chuumong.data.mapper

import space.chuumong.data.remote.model.response.GithubSearchUserResponse
import space.chuumong.domain.entities.GithubSearchUser
import io.reactivex.functions.Function

class GithubUserMapper {

    fun toGithubSearchUserEntity() = Function<GithubSearchUserResponse, GithubSearchUser> {
        it.toEntity()
    }
}