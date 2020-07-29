package com.txsoft.constructioncalculator.ui.main.shapes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.models.Form


class AdapterRecyclerShapes(
    private val context: Context,
    private val forms: List<Form>?,
    private val func: (Form) -> Unit
) :
    RecyclerView.Adapter<AdapterRecyclerShapes.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.iv_shape)

        fun bind(form: Form) {
            image.setImageDrawable(context.getDrawable(form.imageRes))
            image.setOnClickListener {
                func(form)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_shape, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = forms?.size ?: 0

    override fun onBindViewHolder(holder: Holder, position: Int) {
        forms?.run {
            holder.bind(get(position))
        }
    }

}
