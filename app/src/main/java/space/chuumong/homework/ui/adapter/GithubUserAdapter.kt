package space.chuumong.homework.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.chuumong.domain.entities.GithubUser
import space.chuumong.homework.R
import space.chuumong.homework.ui.view.loadCircleImage

class GithubUserAdapter(private val onClick: (GithubUser, Int) -> Unit) :
    RecyclerView.Adapter<GithubUserAdapter.GithubUserViewHolder>() {

    private val users = mutableListOf<GithubUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_github_user, parent, false)
        return GithubUserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): GithubUser {
        return users[position]
    }

    fun addAll(items: List<GithubUser>) {
        users.clear()
        users.addAll(items)
        notifyDataSetChanged()
    }

    fun addMore(items: List<GithubUser>) {
        users.addAll(items)
        notifyDataSetChanged()
    }

    fun add(user: GithubUser) {
        users.add(user)
        notifyDataSetChanged()
    }

    fun delete(user: GithubUser) {
        users.remove(user)
        notifyDataSetChanged()
    }

    fun updateItem(item: GithubUser) {
        val position = users.indexOfFirst { it.name == item.name }
        if (position == -1) {
            return
        }

        users[position] = item
        notifyItemChanged(position)
    }

    inner class GithubUserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val ivProfile = itemView.findViewById<ImageView>(R.id.iv_profile)
        private val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        private val tvScore = itemView.findViewById<TextView>(R.id.tv_score)
        private val ivLike = itemView.findViewById<ImageView>(R.id.iv_like)

        init {
            itemView.setOnClickListener {
                onClick(getItem(adapterPosition), adapterPosition)
            }
        }

        fun bind(item: GithubUser) {
            ivProfile.loadCircleImage(item.profileImage)
            tvName.text = item.name
            tvScore.text = String.format(
                itemView.context.getString(R.string.search_user_score_format),
                item.score.toString()
            )

            if (item.isLike) {
                ivLike.setImageResource(R.drawable.ic_favorite_red_24dp)
            } else {
                ivLike.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            }
        }
    }
}