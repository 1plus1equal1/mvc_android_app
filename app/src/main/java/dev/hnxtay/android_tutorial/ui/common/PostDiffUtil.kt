package dev.hnxtay.android_tutorial.ui.dashboard.recyclerview

import androidx.recyclerview.widget.DiffUtil
import dev.hnxtay.android_tutorial.model.Image

class PostDiffUtil : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Image, newItem: Image) = oldItem == newItem
}