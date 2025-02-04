package kz.applecity.homeworkxml

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.applecity.homeworkxml.databinding.ItemPopularBinding

class PopularAdapter: RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    private val _list: MutableList<PopularItem> = mutableListOf()

    fun submitList(list: List<PopularItem>) {
        _list.clear()
        _list.addAll(list)
    }

    inner class ViewHolder(
        private val binding: ItemPopularBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PopularItem) {
            binding.tvTitle.text = item.title
            binding.tvCount.text = item.countArticles
            binding.ivPicture.setImageResource(item.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPopularBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = _list.get(position)
        holder.bind(item)
    }
}