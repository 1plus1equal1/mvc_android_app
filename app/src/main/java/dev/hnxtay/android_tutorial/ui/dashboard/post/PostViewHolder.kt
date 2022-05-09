package dev.hnxtay.android_tutorial.ui.dashboard.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.hnxtay.android_tutorial.R
import dev.hnxtay.android_tutorial.databinding.ItemRecyclerviewBinding
import dev.hnxtay.android_tutorial.model.Image

class PostViewHolder private constructor(
    private val binding: ItemRecyclerviewBinding,
    private val listener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup, listener: (Int) -> Unit): PostViewHolder {
            val binding =
                ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PostViewHolder(binding, listener)
        }
    }

    init {
        binding.imgPostImage.setOnClickListener {
            listener(absoluteAdapterPosition)
        }
    }

    fun bind(post: Image) {
        with(binding) {
            handleShowInstagramInfo(post.user.instagramUsername)
            Glide.with(imgPostImage.context)
                .load(post.urls.small)
                .placeholder(R.drawable.ic_replay)
                .into(imgPostImage)
        }
    }

    private fun handleShowInstagramInfo(instagramUser: String?) {
        with(binding) {
            if (instagramUser.isNullOrEmpty()) {
                tvInstagram.visibility = View.GONE
                imgInfoBackground.visibility = View.GONE
            } else {
                tvInstagram.visibility = View.VISIBLE
                imgInfoBackground.visibility = View.VISIBLE
                tvInstagram.text = instagramUser
            }
        }
    }
}
