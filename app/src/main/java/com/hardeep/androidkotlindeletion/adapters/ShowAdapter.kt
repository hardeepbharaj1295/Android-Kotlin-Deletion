package com.hardeep.androidkotlindeletion.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.hardeep.androidkotlindeletion.R
import com.hardeep.androidkotlindeletion.database.DatabaseQueries
import com.hardeep.androidkotlindeletion.model.ShowContent.ShowItem

import kotlinx.android.synthetic.main.fragment_show.view.*

class ShowAdapter(
    private val mValues: ArrayList<ShowItem>,context: Context
) : RecyclerView.Adapter<ShowAdapter.ViewHolder>() {

    lateinit var context: Context
    init {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_show, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.name
        holder.mContentView.text = item.email
        holder.mPassword.text = item.password
        holder.mDelete.setOnClickListener {
            val db = DatabaseQueries(context)
            val res = db.deleteUser(item.email)
            if (res>0)
            {
                Toast.makeText(context,"Deleted Successfully",Toast.LENGTH_LONG).show()
                mValues.removeAt(position)
            }
            else
            {
                Toast.makeText(context,"Deleted Not Deleted",Toast.LENGTH_LONG).show()
            }
            // Update array list on gui
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content
        val mPassword: TextView = mView.password
        val mDelete: ImageView = mView.delete

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
