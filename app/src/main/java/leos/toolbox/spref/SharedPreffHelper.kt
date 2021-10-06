package leos.toolbox.spref

import android.content.Context
import leos.toolbox.R

class SharedPreffHelper (ctx:Context) {
    var sharedPref=ctx.getSharedPreferences(ctx.getString(R.string.shared_pref),Context.MODE_PRIVATE)
    var mCtx=ctx

    fun putLoginToken(token: String){
        with(sharedPref.edit()){
            putString(mCtx.getString(R.string.pref_token),token)
            commit()
        }
    }

    fun geLoginToken():String{
        return sharedPref.getString(mCtx.getString(R.string.pref_token),"")!!
    }

}