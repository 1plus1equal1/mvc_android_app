package dev.hnxtay.android_tutorial.ui.dashboard.post

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import dev.hnxtay.android_tutorial.model.Image
import dev.hnxtay.android_tutorial.ui.common.PostDiffUtil

class PostAdapter() : ListAdapter<Image, PostViewHolder>(PostDiffUtil()) {
    internal var listener: (Image) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
