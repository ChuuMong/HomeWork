package space.chuumong.homework.event

import space.chuumong.domain.entities.GithubUser


data class LikeUserEvent(val user: GithubUser)