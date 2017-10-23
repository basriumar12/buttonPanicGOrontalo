package com.blogbasbas.tombolpentinggorontalo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blogbasbas.tombolpentinggorontalo.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class KamusGorontaloActivity extends AppCompatActivity {
    WebView webkamus;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamus_gorontalo);
        webkamus = (WebView) findViewById(R.id.webKamus);

        webkamus.setWebViewClient(new WebViewClient());
        webkamus.getSettings().setJavaScriptEnabled(true);

        webkamus.getSettings().setBuiltInZoomControls(true);
        webkamus.getSettings().setDisplayZoomControls(false);


        //TODO 8 ADMOB
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice("7535AF4EB2936B47ED5B7A27A0547F7F")
                .build();
        //banner
        mAdView = (AdView) findViewById(R.id.adView);
        mAdView.loadAd(adRequest);
        //interstial
        final InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-7486974203577954/7476349470");
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                interstitialAd.show();
            }
        });
        interstitialAd.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        if (webkamus.canGoBack()){
            webkamus.goBack();
        }
        super.onBackPressed();
    }
}
