package msc.fooxer.civilization

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class BigItemFragment : Fragment() {
    lateinit var item: ListItem
    fun newFragment() : Fragment {
        return BigItemFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        val bundle: Bundle? = this.arguments
        if (bundle != null) {
            item = bundle.getParcelable("item")
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.big_item_view, container, false)
        var nameTextView : TextView = view.findViewById(R.id.bigNameTextView) as TextView
        var imageView : ImageView = view.findViewById(R.id.bigImageView) as ImageView
        var helpTextView : TextView = view.findViewById(R.id.bigHelpTextView) as TextView

        nameTextView.text = item.name
        if (item.helptext != null)
            helpTextView.text = item.helptext
        else helpTextView.text = ""
        Picasso.get()
            .load(item.graphic)
            .placeholder(R.drawable.loading)
            .error(R.drawable.error)
            .into(imageView)


            return view
    }

}
