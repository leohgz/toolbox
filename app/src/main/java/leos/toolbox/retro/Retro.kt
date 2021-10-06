package leos.toolbox.retro

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import leos.toolbox.ActivityLogin
import leos.toolbox.spref.SharedPreffHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Retro (ctx:ActivityLogin){
    var mctx=ctx
    val baseUrl= "https://echo-serv.tbxnet.com"
    val compositeDisposable = CompositeDisposable()
    var sharedPreffHelper = SharedPreffHelper(ctx)
    var loggingInterceptor=HttpLoggingInterceptor()
    var comebackImple = ctx

    var httpClient=OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build()

    init {
        loggingInterceptor.level= HttpLoggingInterceptor.Level.BODY

    }
    var retrofit=Retrofit.Builder().
    addConverterFactory(GsonConverterFactory.create()).client(httpClient).
    addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
    baseUrl(baseUrl).build().create(Interfaz::class.java)


    fun doLogin(){
        var pojo = PojoAuth()
        pojo.sub="ToolboxMobileTest"
        var bodyString=""
        bodyString= Gson().toJson(pojo)

        compositeDisposable.add(retrofit
            .login(pojo)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({ onResponseLogin(it)},{ onFailure(it)}))
    }

    fun doData(){
        val headerMap = HashMap<String, String>()
        headerMap.put("authorization",sharedPreffHelper.geLoginToken())

        compositeDisposable.add(retrofit
            .data(headerMap)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({ onResponseData(it) },{ onFailure(it)}))
    }


    fun onResponseLogin(obj:PojoAuth){
        Log.d("retrofit ", "onResponse "+obj.token)
        sharedPreffHelper.putLoginToken(obj.type+" "+obj.token)
        doData()
        Log.d("retrofit ", "onResponseShared: "+sharedPreffHelper.geLoginToken())
    }

    fun onResponseData(obj: List<PojoData>){
        Log.d("retrofit ", "onResponseData "+obj.size)

        comebackImple.onSucces(obj)
    }

    fun onFailure(t: Throwable){
        Log.d("retrofit ", "onFailure "+t.toString())
    }

    interface comeback{
        fun onSucces(obj:List<PojoData>)
    }


}