package msc.fooxer.civilization

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList
import com.squareup.picasso.Picasso

class FragmentList : Fragment() {
    lateinit var itemsArray: ArrayList<ListItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        val bundle: Bundle? = this.arguments
        if (bundle != null)
        {
            itemsArray = bundle.getParcelableArrayList( "itemsarray")
        }

    }
    fun newFragment(): FragmentList {
        return FragmentList()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.item_list, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.list_recyclerView) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false)
        var adapter: CustomAdapter = CustomAdapter(context, itemsArray)
        recyclerView.adapter = adapter
        // подключаем адаптер


        return view
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal var imageView = itemView.findViewById<ImageView>(R.id.item_imageView) as ImageView
        internal var nameTextView = itemView.findViewById<TextView>(R.id.nameTextView) as TextView
        internal var helpTextView = itemView.findViewById<TextView>(R.id.helpTextView) as TextView

        override fun onClick(v: View?) {
            //открытие окна информации
        }

        fun onBindView (item: ListItem) {
            nameTextView.text = item.name
            if(item.helptext != null)
                helpTextView.text = item.helptext
            else helpTextView.text = ""
            Picasso.get()
                .load(item.graphic)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(imageView)

        }
    }
    inner class CustomAdapter internal constructor(context: Context?, items: ArrayList<ListItem>) : RecyclerView.Adapter<FragmentList.ViewHolder>() {

        val items = items
        val context = context

        override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
            val view: View = LayoutInflater.from(context).inflate(R.layout.item_view, viewGroup, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
        return items.size
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            var item: ListItem = items[position]
            viewHolder.onBindView(item)
        }

    }
}