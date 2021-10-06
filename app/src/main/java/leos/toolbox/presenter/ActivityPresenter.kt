package leos.toolbox.presenter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import leos.toolbox.ActivityLogin
import leos.toolbox.R
import leos.toolbox.rcv.RcvAdapter
import leos.toolbox.retro.PojoData

class ActivityPresenter (act:ActivityLogin, obj: List<PojoData>) {
    var actLogin = act
    var rcv: RecyclerView

    init {
        rcv = act.findViewById(R.id.rcv)
        rcv.setHasFixedSize(false)
        rcv.layoutManager = LinearLayoutManager(act,LinearLayoutManager.VERTICAL,false)
     //   rcv.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
      //  rcv.layoutManager = GridLayoutManager(act,1,GridLayoutManager.VERTICAL,false)
        rcv.adapter = RcvAdapter(act,obj)
    }




}