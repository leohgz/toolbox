package leos.toolbox.retro

import io.reactivex.Observable
import retrofit2.http.*

interface Interfaz {
    @POST ("/v1/mobile/auth")
    fun login(@Body body:PojoAuth):Observable<PojoAuth>

    @GET ("/v1/mobile/data")
    fun data(@HeaderMap headers: MutableMap<String, String>):Observable<List<PojoData>>
}