package dev.hnxtay.android_tutorial.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dev.hnxtay.android_tutorial.R
import dev.hnxtay.android_tutorial.databinding.ActivityDetailsBinding
import dev.hnxtay.android_tutorial.model.Image

class DetailsActivity : AppCompatActivity() {
    companion object {
        const val IMAGE_DATA_KEY = "image_data_key"
    }

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showInformation()
        clickBack()
    }

    private fun showInformation() {
        val imageData = intent.getParcelableExtra<Image>(IMAGE_DATA_KEY)

        with(binding) {
            Glide.with(imgDetail.context)
                .load(imageData?.urls?.small)
                .placeholder(R.drawable.ic_replay)
                .into(imgDetail)

            tvLike.text = imageData?.likes.toString()

            imageData?.user?.run {
                tvInstagramDetail.text = checkResponseData(instagramUsername)
                tvTwitterDetail.text = checkResponseData(twitterUsername)
                tvUrl.text = checkResponseData(portfolioUrl)
                tvBio.text = checkResponseData(bio)
            }
        }
    }

    private fun checkResponseData(data: String?): String? {
        val text = if (data.isNullOrEmpty()) {
            getString(R.string.label_text_view_no_data)
        } else {
            data
        }
        return text
    }

    private fun clickBack() {
        binding.imgBack.setOnClickListener {
            finish()
        }
    }
}
