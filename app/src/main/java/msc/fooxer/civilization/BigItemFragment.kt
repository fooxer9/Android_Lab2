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
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlinx.android.synthetic.main.item_view.*

class BigItemFragment : Fragment() {
    lateinit var item: ListItem
    lateinit var nameTextView : TextView
    lateinit var imageView : ImageView
    lateinit var helpTextView : TextView
    fun newFragment() : Fragment {
        return BigItemFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true // сохраняет состояние фрагмента при повороте экрана
        val bundle: Bundle? = this.arguments //это типа интента для фрагментов - в нем хранятся временные файлы - обмен файлами между фрагментами
        if (bundle != null) {
            item = bundle.getParcelable("item") // достаем объект
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.big_item_view, container, false)
        nameTextView = view.findViewById(R.id.bigNameTextView) as TextView
        imageView = view.findViewById(R.id.bigImageView) as ImageView
        helpTextView = view.findViewById(R.id.bigHelpTextView) as TextView

        nameTextView.text = item.name
        if (item.helptext != null) // если есть описание - вывести
            helpTextView.text = item.helptext
        else helpTextView.text = ""
        Picasso.get() // загрузка картинки
            .load(item.graphic)
            .placeholder(R.drawable.loading)
            .error(R.drawable.error)
            .into(imageView)


            return view
    }

}
