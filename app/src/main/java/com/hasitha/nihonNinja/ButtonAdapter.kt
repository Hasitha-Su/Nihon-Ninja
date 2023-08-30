package com.hasitha.nihonNinja

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class ButtonAdapter(private val buttonTexts: List<String>) :
    RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {

    private var calculatedButtonWidth: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_button, parent, false)
        return ButtonViewHolder(view)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        val buttonText = buttonTexts[position]
        holder.button.text = buttonText

        if (calculatedButtonWidth == 0) {
            val displayMetrics = holder.itemView.resources.displayMetrics
            val screenWidth = displayMetrics.widthPixels

            // Calculate the maximum number of buttons in a line
            val maxButtonsInLine = screenWidth / holder.itemView.resources.getDimensionPixelSize(R.dimen.button_width)

            // Calculate the calculated width of each button
            calculatedButtonWidth = screenWidth / maxButtonsInLine
        }

        val layoutParams = holder.button.layoutParams
        layoutParams.width = calculatedButtonWidth
        holder.button.layoutParams = layoutParams
    }

    override fun getItemCount(): Int = buttonTexts.size

    class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button: Button = itemView.findViewById(R.id.buttonX)
    }
}
