package id.aasumitro.dicodingbeginner.ui.main.fragment.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.aasumitro.dicodingbeginner.ui.main.fragment.home.holder.HorizontalViewHolder
import id.aasumitro.dicodingbeginner.ui.main.fragment.home.listener.HorizontalListener
import id.aasumitro.dicodingbeginner.R
import id.aasumitro.dicodingbeginner.data.model.Headline

/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */


class HorizontalAdapter (private val dataList: ArrayList<Headline>,
                         private val listener: HorizontalListener) :
        RecyclerView.Adapter<HorizontalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_card_horizontal, parent, false)
        return HorizontalViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) =
            holder.bind(dataList[position], listener)

    override fun getItemCount(): Int = dataList.count()

}