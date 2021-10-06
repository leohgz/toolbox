package leos.toolbox

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.webkit.WebView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import leos.toolbox.presenter.ActivityPresenter
import leos.toolbox.retro.PojoData
import leos.toolbox.retro.Retro
import okhttp3.internal.notifyAll

class ActivityLogin : AppCompatActivity(),Retro.comeback {
    var prsnter:ActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var request=Retro(this)
        request.doLogin()

        val pd = ProgressDialog(this)
        pd.setMessage(getString(R.string.loading))
        pd.show()


        val timer: CountDownTimer = object: CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                pd.dismiss()
            }
        }
        timer.start()
    }

    override fun onSucces(obj: List<PojoData>) {
        prsnter = ActivityPresenter(this,obj)
    }

}