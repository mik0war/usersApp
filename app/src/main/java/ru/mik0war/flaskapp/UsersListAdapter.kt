package ru.mik0war.flaskapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.mik0war.flaskapp.databinding.ItemLayoutBinding

class UsersListAdapter(
    private val baseUrl: String,
    private var list: List<UserData>,
    private val lambda : (id: Int) -> Unit
) : RecyclerView.Adapter<UsersListViewHolder>(){

    fun update(newList: List<UserData>){
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context))

        return UsersListViewHolder(baseUrl, binding, lambda)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

}

class UsersListViewHolder(
    private val baseUrl : String,
    private val binding: ItemLayoutBinding,
    private val lambda : (id: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root){

    fun bind(userData: UserData){
        binding.loginView.text = userData.login
        binding.passView.text = userData.password

        Picasso.get()
            .load(baseUrl + userData.link)
            .into(binding.imageView)

        binding.root.setOnClickListener {
            lambda(userData.id)
        }
    }

}

















