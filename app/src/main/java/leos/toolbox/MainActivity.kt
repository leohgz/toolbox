package leos.toolbox

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pd = ProgressDialog(this)
        pd.setMessage(getString(R.string.loading))
        pd.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd.show()

        val timer:CountDownTimer = object: CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                pd.dismiss()
                startActivity(Intent(baseContext,ActivityLogin::class.java))
                finish()
            }
        }
        timer.start()

    }
}