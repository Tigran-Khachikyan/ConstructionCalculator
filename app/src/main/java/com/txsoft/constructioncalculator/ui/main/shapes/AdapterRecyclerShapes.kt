package com.txsoft.constructioncalculator.ui.main.shapes

import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.models.Form
import kotlin.math.round


class AdapterRecyclerShapes(
    private val context: Context,
    private val forms: List<Form>?,
    private val func: (Form) -> Unit
) :
    RecyclerView.Adapter<AdapterRecyclerShapes.Holder>() {

    private val params by lazy {
        val sideInDp = (getScreenWidthInDp() - 1) / 3
        val sideInPx = round(sideInDp * context.resources.displayMetrics.density).toInt()
        ViewGroup.LayoutParams(sideInPx, sideInPx)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.iv_shape)

        fun bind(form: Form) {
            itemView.layoutParams = params
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

    private fun getScreenWidthInDp(): Int {
        val dm = DisplayMetrics()
        val windowsManager = context.getSystemService(WINDOW_SERVICE) as WindowManager
        windowsManager.defaultDisplay.getMetrics(dm)
        return round(dm.widthPixels / dm.density).toInt()
    }

}
