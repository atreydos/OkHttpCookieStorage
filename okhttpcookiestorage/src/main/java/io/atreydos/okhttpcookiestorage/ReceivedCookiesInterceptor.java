package io.atreydos.okhttpcookiestorage;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author Vlad Derevyanko
 * @since 8/10/18 in 17:57
 */
public class ReceivedCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>(originalResponse.headers("Set-Cookie"));
            CookieStorage.getInstance().putCookies(cookies);
        }

        return originalResponse;
    }
}