package msc.fooxer.civilization

import android.os.Parcel
import android.os.Parcelable

class ListItem(image: String, name:String, helptext: String? ) : Parcelable {
    var graphic: String = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/"
    val name: String
    val helptext: String?

      init {
        this.name = name
        this.helptext = helptext
        this.graphic += image
    }

    override fun writeToParcel(parsel: Parcel?, flags: Int) {
        if (parsel != null) {
            parsel.writeString(graphic)
            parsel.writeString(name)
            parsel.writeString(helptext)


        };
    }

    override fun describeContents(): Int { return 0;}

    companion object CREATOR : Parcelable.Creator<ListItem> {
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