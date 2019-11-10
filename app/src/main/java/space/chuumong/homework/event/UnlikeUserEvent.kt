package space.chuumong.homework.event

import space.chuumong.domain.entities.GithubUser

data class UnlikeUserEvent(val user: GithubUser)