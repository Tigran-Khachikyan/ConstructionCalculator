package com.txsoft.constructioncalculator.ui.main

import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.models.enums.Form
import kotlin.math.round


class AdapterRecyclerShapes(
    private val context: Context,
    private val forms: List<Form>?,
    private val inCalculation: Boolean,
    private val marked: Boolean,
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
            if (!marked)
                itemView.layoutParams = params
            image.apply {
                isFocusable = inCalculation
                isClickable = inCalculation
                if (inCalculation) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        val outValue = TypedValue()
                        context.theme.resolveAttribute(
                            android.R.attr.selectableItemBackground, outValue, true
                        )
                        foreground = context.getDrawable(outValue.resourceId)
                    }
                }
                if (!marked)
                    setImageDrawable(context.getDrawable(form.imageRes))
                else
                    setImageDrawable(context.getDrawable(form.markedImageRes))

                setOnClickListener {
                    if (inCalculation)
                        forms?.run { func(get(adapterPosition)) }
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val res = if (!marked) R.layout.holder_shape else R.layout.holder_shape_marked
        val view = LayoutInflater.from(parent.context).inflate(res, parent, false)
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
