package msc.fooxer.civilization

import android.os.Parcel
import android.os.Parcelable

class ListItem(image: String, name:String, helptext: String? ) : Parcelable {
    val graphic: String
    val name: String
    val helptext: String?

      init {
        this.name = name
        this.helptext = helptext
        this.graphic = image
    }

    override fun writeToParcel(parsel: Parcel?, flags: Int) { // парсел позволяет передавать объекты, а не простые типы в интент
        if (parsel != null) { // тут мы создаем объект парсел, в который переносим свойства передаваемого объекта
            parsel.writeString(graphic)
            parsel.writeString(name)
            parsel.writeString(helptext)


        };
    }

    override fun describeContents(): Int { return 0;}

    companion object CREATOR : Parcelable.Creator<ListItem> { // обратное восстановление объекта из парсела
        override fun createFromParcel(parcel: Parcel): ListItem {
            var image = parcel.readString()
            var name = parcel.readString()
            var helptext = parcel.readString()


            return ListItem(image,name,helptext)
        }

        override fun newArray(size: Int): Array<ListItem?> {
            return arrayOfNulls(size)
        }
    }
}