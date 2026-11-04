package lat.pam.kopidannyemil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(
    private var items: List<MenuItem>,
    private val onCartChanged: () -> Unit
) : RecyclerView.Adapter<MenuAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvEmoji: TextView = view.findViewById(R.id.tvEmoji)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvDesc: TextView = view.findViewById(R.id.tvDesc)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val btnAdd: TextView = view.findViewById(R.id.btnAdd)
        val layoutQty: View = view.findViewById(R.id.layoutQty)
        val btnMinus: TextView = view.findViewById(R.id.btnMinus)
        val tvQty: TextView = view.findViewById(R.id.tvQty)
        val btnPlus: TextView = view.findViewById(R.id.btnPlus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu, parent, false)
        return VH(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.tvEmoji.text = item.emoji
        holder.tvName.text = item.name
        holder.tvDesc.text = "• " + item.desc
        holder.tvPrice.text = DummyData.rupiah(item.price)

        val qty = DummyData.qty(item)
        holder.btnAdd.visibility = if (qty == 0) View.VISIBLE else View.GONE
        holder.layoutQty.visibility = if (qty == 0) View.GONE else View.VISIBLE
        holder.tvQty.text = qty.toString()

        holder.btnAdd.setOnClickListener { change(holder) { DummyData.addToCart(item) } }
        holder.btnPlus.setOnClickListener { change(holder) { DummyData.addToCart(item) } }
        holder.btnMinus.setOnClickListener { change(holder) { DummyData.removeFromCart(item) } }
    }

    private fun change(holder: VH, action: () -> Unit) {
        action()
        val pos = holder.bindingAdapterPosition
        if (pos != RecyclerView.NO_POSITION) notifyItemChanged(pos)
        onCartChanged()
    }

    fun submit(newItems: List<MenuItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}