package id.aasumitro.dicodingbeginner.ui.main.fragment.home.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import id.aasumitro.dicodingbeginner.R
import id.aasumitro.dicodingbeginner.data.model.Everything
import id.aasumitro.dicodingbeginner.ui.main.fragment.home.listener.VerticalListener
import id.aasumitro.dicodingbeginner.utlil.AppConst
import id.aasumitro.dicodingbeginner.utlil.BitmapTransform
import id.aasumitro.dicodingbeginner.utlil.dateHelper
import id.aasumitro.dicodingbeginner.utlil.source
import kotlinx.android.synthetic.main.item_card_vertical.view.*

/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */

class VerticalViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(everything: Everything, listener: VerticalListener, context: Context) {

        Picasso.get()
                .load(everything.urlToImage)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_broken_image)
                .transform(BitmapTransform(AppConst.MAX_WIDTH, AppConst.MAX_HEIGHT))
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .resize(AppConst.SIZE, AppConst.SIZE)
                .centerInside()
                .into(itemView.img_vertical)
        itemView.txt_vertical_title.text = everything.title
        val mSource = "("+source(context.applicationContext)?.toUpperCase()+")"
        itemView.txt_vertical_source.text = mSource
        if (everything.author !== null) {
            itemView.txt_vertical_author.text = everything.author
            val bbcNews = "https://www.facebook.com/bbcnews"
            if (everything.author!! == bbcNews)
                itemView.txt_vertical_author.visibility = View.GONE
        } else {
            itemView.txt_vertical_author.visibility = View.GONE
        }
        itemView.txt_vertical_published.text = dateHelper(everything.publishedAt)
        itemView.lay_vertical_click.setOnClickListener { listener.onVerticalItemClick(everything) }

    }

}