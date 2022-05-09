package dev.hnxtay.android_tutorial.ui.dashboard.post

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import dev.hnxtay.android_tutorial.model.Image
import dev.hnxtay.android_tutorial.ui.common.PostDiffUtil

class PostAdapter(private val listener: (Int) -> Unit) :
    ListAdapter<Image, PostViewHolder>(PostDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val imageItem = getItem(position)
        holder.bind(imageItem)
    }
}
