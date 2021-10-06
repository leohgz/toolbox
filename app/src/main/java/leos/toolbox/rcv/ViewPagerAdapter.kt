package leos.toolbox.rcv

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import leos.toolbox.retro.PojoData

class ViewPagerAdapter(obj:PojoData,fm:FragmentManager) : FragmentStatePagerAdapter(fm
) {
    var theObj=obj
    var type=theObj.type

    init {
    }
    override fun getCount(): Int {
        return theObj.items.size
    }

    override fun getItem(position: Int): Fragment {
        val currentItem = theObj.items.get(position)
        return ViewPagerFragmen(type,currentItem)
    }

}