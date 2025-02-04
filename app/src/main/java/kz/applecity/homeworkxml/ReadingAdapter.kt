package kz.applecity.homeworkxml

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.applecity.homeworkxml.databinding.ItemPopularBinding
import kz.applecity.homeworkxml.databinding.ItemReadingBinding

class ReadingAdapter: RecyclerView.Adapter<ReadingAdapter.ViewHolder>() {

    private val _list: MutableList<ReadingItem> = mutableListOf()

    fun submitList(list: List<ReadingItem>) {
        _list.clear()
        _list.addAll(list)
    }

    inner class ViewHolder(
        private val binding: ItemReadingBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ReadingItem) {
            binding.tvTitle.text = item.title
            binding.tvType.text = item.type
            binding.tvAuthorAndTime.text = item.authorAndTime
            binding.ivPicture.setImageResource(item.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReadingBinding.inflate(inflater, parent, false)
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