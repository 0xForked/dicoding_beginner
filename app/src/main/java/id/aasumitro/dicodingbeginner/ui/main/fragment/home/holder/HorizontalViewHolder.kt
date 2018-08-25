package id.aasumitro.dicodingbeginner.ui.main.fragment.home.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import id.aasumitro.dicodingbeginner.R
import id.aasumitro.dicodingbeginner.data.model.Headline
import id.aasumitro.dicodingbeginner.data.preferences.SharedPreferences
import id.aasumitro.dicodingbeginner.data.preferences.SharedPreferences.get
import id.aasumitro.dicodingbeginner.ui.main.fragment.home.listener.HorizontalListener
import id.aasumitro.dicodingbeginner.utlil.AppConst
import id.aasumitro.dicodingbeginner.utlil.AppConst.MAX_HEIGHT
import id.aasumitro.dicodingbeginner.utlil.AppConst.MAX_WIDTH
import id.aasumitro.dicodingbeginner.utlil.AppConst.SIZE
import id.aasumitro.dicodingbeginner.utlil.BitmapTransform
import kotlinx.android.synthetic.main.item_card_horizontal.view.*

/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */


class HorizontalViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(headline: Headline, listener: HorizontalListener) {

        Picasso.get()
                .load(headline.urlToImage)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_broken_image)
                .transform(BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .resize(SIZE, SIZE)
                .centerInside()
                .into(itemView.img_horizontal)
        itemView.txt_horizontal_title.text = headline.title
        itemView.lay_horizontal_click.setOnClickListener { listener.onHorizontalItemClick(headline) }

    }

}