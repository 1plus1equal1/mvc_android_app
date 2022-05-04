package dev.hnxtay.android_tutorial.ui.main.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import dev.hnxtay.android_tutorial.Image

class PostAdapter : ListAdapter<Image, PostViewHolder>(PostDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
