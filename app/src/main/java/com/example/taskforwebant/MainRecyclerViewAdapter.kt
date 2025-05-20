package com.example.taskforwebant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.PostForUser
import com.example.taskforwebant.MainRecyclerViewAdapter.PostHolder
import com.example.taskforwebant.databinding.PostItemBinding

class MainRecyclerViewAdapter(): RecyclerView.Adapter<PostHolder>() {

    val postList = mutableListOf<PostForUser>()

    class PostHolder(postItem: View): RecyclerView.ViewHolder(postItem){
        val binding = PostItemBinding.bind(postItem)
        fun bind(post: PostForUser){
            binding.textView.text = post.title
            binding.textView2.text = post.body
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(
        holder: PostHolder,
        position: Int
    ) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun getNewPosts(newPostList: List<PostForUser>){
        postList.addAll(newPostList)
        notifyDataSetChanged()
    }
}