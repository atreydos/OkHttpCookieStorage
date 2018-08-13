package io.atreydos.okhttpcookiesstorageexample.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.atreydos.okhttpcookiesstorageexample.R;
import io.atreydos.okhttpcookiesstorageexample.api.ApiFactory;
import io.atreydos.okhttpcookiesstorageexample.api.BaseResponse;
import io.atreydos.okhttpcookiestorage.CookieStorage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Vlad Derevyanko
 * @since 8/13/18 in 18:13
 */
@SuppressLint("SetTextI18n")
public class MainActivity extends Activity implements View.OnClickListener {

    private TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);
        findViewById(R.id.btnMakeRequest).setOnClickListener(this);
        findViewById(R.id.btnClearStorage).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayCookiesFromStorage();
    }


    private void callApi() {
        ApiFactory.getApiService("https://mydocs.cloud/").checkApi().enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                displayCookiesFromStorage();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                tvDisplay.setText("Networking error!\n" + t.getLocalizedMessage());
            }
        });
    }

    private void displayCookiesFromStorage() {
        if (CookieStorage.getInstance().getCookies().isEmpty()) {
            tvDisplay.setText("No cookies in storage!");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String cookie : CookieStorage.getInstance().getCookies())
                sb.append(cookie).append('\n');

            tvDisplay.setText(sb.toString());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnMakeRequest:
                callApi();
                break;
            case R.id.btnClearStorage:
                CookieStorage.getInstance().clear();
                displayCookiesFromStorage();
                break;
        }
    }
}
