package io.atreydos.okhttpcookiesstorageexample.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Vlad Derevyanko
 * @since 8/13/18 in 18:20
 */
public interface ApiService {

    @GET("api/")
    Call<BaseResponse> checkApi();

}
