package leos.toolbox.rcv
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import leos.toolbox.R
import leos.toolbox.retro.PojoData


class RcvAdapter(ctx:AppCompatActivity, list:List<PojoData>): RecyclerView.Adapter<RcvAdapter.ViewHolder>() {
    var mCtx=ctx
    var inflater = LayoutInflater.from(ctx)
    var cList=list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(viewType,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val current = cList.get(position)
        var adapter = ViewPagerAdapter(current,mCtx.supportFragmentManager)

      //  holder.viewpager.adapter = adapter
        holder.carousel_title.text=current.title
        holder.viewpager.adapter=adapter


    }

    override fun getItemViewType(position: Int): Int {
        val current = cList.get(position)
        if (current.type.trim().toLowerCase().equals("thumb"))
            return R.layout.rcv_item_thumb
        else
            return R.layout.rcv_item

    }
    override fun getItemCount(): Int {
        return cList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
         var viewpager: ViewPager
         var carousel_title:TextView

        init {
             viewpager = itemView.findViewById(R.id.viewpager123)
             carousel_title=itemView.findViewById(R.id.carousel_title)
        }
    }


}