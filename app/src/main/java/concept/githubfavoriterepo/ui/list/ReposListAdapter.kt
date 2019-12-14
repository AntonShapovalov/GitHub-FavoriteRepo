package concept.githubfavoriterepo.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import concept.githubfavoriterepo.R
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * List adapter for [ReposListFragment]
 */
class ReposListAdapter(private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<ReposListAdapter.ViewHolder>() {

    private val items = arrayListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val view = holder.itemView
        view.textViewRepositoryName.text = "Item # $item"
        view.setOnClickListener { onItemClick(position) }
    }

    override fun getItemCount(): Int = items.size

//    fun setItems(newItems: List<Int>) {
//        items.clear()
//        items.addAll(newItems)
//        notifyDataSetChanged()
//    }
//
//    fun clearItems() {
//        items.clear()
//        notifyDataSetChanged()
//    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}