package io.atreydos.okhttpcookiestorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashSet;

/**
 * @author Vlad Derevyanko
 * @since 8/13/18 in 13:24
 */
public class CookieStorage {

    private static CookieStorage instance;
    private static final String OK_HTTP_COOKIES = "OK_HTTP_COOKIES";
    private SharedPreferences mPreferences = null;

    private CookieStorage(Context context) {
        this.mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void init(Context context) {
        instance = new CookieStorage(context);
    }

    public static CookieStorage getInstance() {
        if (instance == null)
            throw new NullPointerException("CookieStorage must be initialized in your Application.class");
        return instance;
    }

    public HashSet<String> getCookies() {
        return (HashSet<String>) mPreferences.getStringSet(OK_HTTP_COOKIES, new HashSet<String>());
    }

    public boolean putCookies(HashSet<String> cookies) {
        HashSet<String> storedCookies = getCookies();
        storedCookies.addAll(cookies);

        SharedPreferences.Editor ed = mPreferences.edit();
        ed.putStringSet(OK_HTTP_COOKIES, cookies).apply();
        return ed.commit();
    }

    public boolean clear() {
        return mPreferences.edit().remove(OK_HTTP_COOKIES).commit();
    }
}
