package io.atreydos.okhttpcookiestorage;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;


/**
 * @author Vlad Derevyanko
 * @since 8/10/18 in 17:25
 */
// this interceptor is used after the normal logging of OkHttp;
public class AddCookiesInterceptor implements Interceptor {

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        for (String cookie : CookieStorage.getInstance().getCookies()) {
            builder.addHeader("Cookie", cookie);
            Log.v("OkHttp", "Adding Header: " + cookie);
        }

        return chain.proceed(builder.build());
    }
}
