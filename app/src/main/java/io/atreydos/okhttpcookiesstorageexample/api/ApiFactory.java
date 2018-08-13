package io.atreydos.okhttpcookiesstorageexample.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.atreydos.okhttpcookiestorage.AddCookiesInterceptor;
import io.atreydos.okhttpcookiestorage.ReceivedCookiesInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Vlad Derevyanko
 * @since 8/13/18 in 18:19
 */
public final class ApiFactory {

    private static ApiService sService;
    private static OkHttpClient sClient;
    private static Gson sGson;
    private static String sBaseUrl;

    public static ApiService getApiService(final String baseUrl) {
        //I know that double checked locking is not a good pattern, but it's enough here
        ApiService service = sService;
        if (service == null || !sBaseUrl.equals(baseUrl)) {
            synchronized (ApiFactory.class) {
                sBaseUrl = baseUrl;
                service = sService = createApiService(baseUrl);
            }
        }
        return service;
    }

    private static ApiService createApiService(final String erpUrl) {
        return new Retrofit.Builder()
                .baseUrl(erpUrl)
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build()
                .create(ApiService.class);
    }

    private static Gson getGson() {
        Gson gson = sGson;
        if (gson == null) {
            synchronized (ApiFactory.class) {
                gson = sGson;
                if (gson == null) {
                    gson = sGson = buildGson();
                }
            }
        }
        return gson;
    }

    private static OkHttpClient getHttpClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (ApiFactory.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildHttpClient();
                }
            }
        }
        return client;
    }

    private static Gson buildGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
    }


    private static OkHttpClient buildHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(new ReceivedCookiesInterceptor())
                .build();
    }
}
