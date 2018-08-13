package io.atreydos.okhttpcookiesstorageexample;

import android.app.Application;

import io.atreydos.okhttpcookiestorage.CookieStorage;

/**
 * @author Vlad Derevyanko
 * @since 8/13/18 in 18:13
 */
public class Global extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CookieStorage.init(this);
    }
}
