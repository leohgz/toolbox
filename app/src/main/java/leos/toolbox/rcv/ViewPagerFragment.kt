package leos.toolbox.rcv
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import leos.toolbox.R
import leos.toolbox.retro.Item
import java.lang.Exception

class ViewPagerFragmen(objP: String, get: Item) : Fragment() {
    var obj = get
    var type= objP
    var img: ImageView?=null
    lateinit var textof:TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        if (type.equals("poster"))
            return inflater.inflate(R.layout.view_pager_item,container,false)
        else
            return inflater.inflate(R.layout.view_pager_item2,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var url = obj.imageUrl
        if (type.equals("poster"))
            img =  view.findViewById(R.id.appcompatimg123)

        else
            img =  view.findViewById(R.id.appcompatimg123)

         try {
                Picasso.with(activity).load(url).into(img)
            }catch (e:Exception){}

        textof =  view.findViewById<TextView>(R.id.image_title)
        textof.text=obj.title
        Log.d("Picasso ", "url "+url)

        img?.setOnClickListener({
            if (obj.imageUrl!=null && !obj.imageUrl.trim().equals(""))
                showPopUp()
            else{
                Toast.makeText(activity,"No hay video disponible!",Toast.LENGTH_LONG).show()
            }
        })
    }

    fun showPopUp(){
        var dialog = activity?.let { Dialog(it) }
        dialog?.setContentView(R.layout.dialog)
        var webview =  dialog?.findViewById<WebView>(R.id.webview_on_dialog)
        webview?.loadUrl(obj.videoUrl)
        dialog?.setOnDismissListener({
            webview?.stopLoading()
        })
        dialog?.show()
    }
}