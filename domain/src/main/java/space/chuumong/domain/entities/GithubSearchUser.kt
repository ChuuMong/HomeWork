package space.chuumong.domain.entities


data class GithubSearchUser(
    val totalCount: Int,
    val users: List<GithubUser>
)

data class GithubUser(
    val name: String,
    val profileImage: String,
    val score: Float,
    var isLike: Boolean = false
)