package com.assignment_four.assignmentfourparttwo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private var contactList: List<Contact>,
    private val onItemClick: (Contact) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private var filteredList: List<Contact> = contactList

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvContactName)
        val tvPhone: TextView = itemView.findViewById(R.id.tvContactPhone)
        val tvEmail: TextView = itemView.findViewById(R.id.tvContactEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = filteredList[position]
        holder.tvName.text = contact.name
        holder.tvPhone.text = "Phone: ${contact.phone}"
        holder.tvEmail.text = "Email: ${contact.email}"
        
        holder.itemView.setOnClickListener {
            onItemClick(contact)
        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    fun updateList(newList: List<Contact>) {
        contactList = newList
        filteredList = newList
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            contactList
        } else {
            contactList.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}
