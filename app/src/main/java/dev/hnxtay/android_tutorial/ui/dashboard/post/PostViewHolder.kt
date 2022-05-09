package dev.hnxtay.android_tutorial.ui.dashboard.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.hnxtay.android_tutorial.R
import dev.hnxtay.android_tutorial.databinding.ItemRecyclerviewBinding
import dev.hnxtay.android_tutorial.model.Image

class PostViewHolder private constructor(
    private val binding: ItemRecyclerviewBinding,
    private val listener: (Image) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup, listener: (Image) -> Unit): PostViewHolder {
            val binding =
                ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PostViewHolder(binding, listener)
        }
    }

    fun bind(post: Image) {
        with(binding) {
            textCaption.text = post.description
            Glide.with(image.context).load(post.urls.small)
                .placeholder(R.drawable.ic_replay).into(image)
            binding.image.setOnClickListener {
                listener(post)
            }
        }
    }

}

