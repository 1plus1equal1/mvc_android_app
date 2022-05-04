package dev.hnxtay.android_tutorial.ui.main.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.hnxtay.android_tutorial.Image
import dev.hnxtay.android_tutorial.R
import dev.hnxtay.android_tutorial.databinding.ItemRecyclerviewBinding

class PostViewHolder private constructor(private val binding: ItemRecyclerviewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): PostViewHolder {
            val binding =
                ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PostViewHolder(binding)
        }
    }

    fun bind(post: Image) {
        with(binding) {
            textCaption.text = post.description
            Glide.with(image.context).load(post.urls.small)
                .placeholder(R.drawable.ic_replay).into(image)
        }
    }

}

