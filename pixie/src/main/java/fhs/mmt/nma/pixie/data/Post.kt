package fhs.mmt.nma.pixie.data

data class Post(
    val photos: List<Photography>,
    val author: Photographer,
    val likes: Int,
    val comments: List<Comment>
)

fun Post.showLikesNumber() = displayNumberShortcut(likes)
fun Post.showCommentsNumber() = displayNumberShortcut(comments.size)

fun displayNumberShortcut(number: Int) =
    if (number > 1_000_000)
        "${(number/1_000_000)}M+"
    else
        if (number > 10_000)
            "${(number/1000)}K+"
        else
            number.toString()