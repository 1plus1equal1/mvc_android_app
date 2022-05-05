package dev.hnxtay.android_tutorial.ui.main.recyclerview

import androidx.recyclerview.widget.DiffUtil
import dev.hnxtay.android_tutorial.models.Image

class PostDiffUtil : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Image, newItem: Image) = oldItem == newItem
}