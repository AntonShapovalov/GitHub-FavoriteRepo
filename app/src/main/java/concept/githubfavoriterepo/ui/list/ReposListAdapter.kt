package concept.githubfavoriterepo.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import concept.githubfavoriterepo.R
import concept.githubfavoriterepo.data.RepoEntry
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * List adapter for [ReposListFragment]
 */
class ReposListAdapter(private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<ReposListAdapter.ViewHolder>() {

    private val items: ArrayList<RepoEntry> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val holder = ViewHolder(v)
        v.setOnClickListener {
            val position = holder.adapterPosition
            if (position >= 0) onItemClick(position)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val view = holder.itemView
        view.textViewRepositoryName.text = item.name
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<RepoEntry>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}