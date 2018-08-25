package id.aasumitro.dicodingbeginner.ui.main.fragment.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.aasumitro.dicodingbeginner.R
import id.aasumitro.dicodingbeginner.data.model.Everything
import id.aasumitro.dicodingbeginner.ui.main.fragment.home.holder.VerticalViewHolder
import id.aasumitro.dicodingbeginner.ui.main.fragment.home.listener.VerticalListener

/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */

class VerticalAdapter (private val dataList: ArrayList<Everything>,
                         private val listener: VerticalListener) :
        RecyclerView.Adapter<VerticalViewHolder>() {

    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_card_vertical, parent, false)
        mContext = parent.context
        return VerticalViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) =
            holder.bind(dataList[position], listener, mContext!!)

    override fun getItemCount(): Int = dataList.count()

}