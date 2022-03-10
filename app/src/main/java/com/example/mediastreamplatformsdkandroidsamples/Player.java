package com.example.mediastreamplatformsdkandroidsamples;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.exoplayer2.upstream.RawResourceDataSource;

import org.json.JSONObject;

import am.mediastre.mediastreamplatformsdkandroid.MediastreamMiniPlayerConfig;
import am.mediastre.mediastreamplatformsdkandroid.MediastreamPlayer;
import am.mediastre.mediastreamplatformsdkandroid.MediastreamPlayerCallback;
import am.mediastre.mediastreamplatformsdkandroid.MediastreamPlayerConfig;
import am.mediastre.mediastreamplatformsdkandroid.MediastreamPlayerService;

public class Player extends AppCompatActivity {
    private String TAG = "SampleApp";
    private FrameLayout container;
    private MediastreamPlayer player;
    private MediastreamPlayerConfig config;
    private Boolean isService = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        config = new MediastreamPlayerConfig();

        Intent intent = getIntent();
        if (intent.getStringExtra("id") != null) {
            config.id = intent.getStringExtra("id");
        }

        if (intent.getStringExtra("accountID") != null) {
            config.accountID = intent.getStringExtra("accountID");
        }

        Boolean isLocal = intent.getBooleanExtra("isLocal", false);

        Boolean isLive = intent.getBooleanExtra("isLive", false);

        switch (intent.getStringExtra("type")) {
            case "video":
                if (isLive) {
                    config.type = MediastreamPlayerConfig.VideoTypes.LIVE;
                } else {
                    config.type = MediastreamPlayerConfig.VideoTypes.VOD;
                }
                if (isLocal) {
                    config.src = RawResourceDataSource.buildRawResourceUri(R.raw.test).toString();
                }
                break;
            case "audio":
                config.playerType = MediastreamPlayerConfig.PlayerType.AUDIO;
                if (isLive) {
                    config.type = MediastreamPlayerConfig.VideoTypes.LIVE;
                } else {
                    config.type = MediastreamPlayerConfig.VideoTypes.VOD;
                }
                if (isLocal) {
                    config.src = RawResourceDataSource.buildRawResourceUri(R.raw.noname).toString();
                }
                break;
            case "episode":
                config.type = MediastreamPlayerConfig.VideoTypes.EPISODE;
                config.loadNextAutomatically = true;
                break;
            default:
                config.type = MediastreamPlayerConfig.VideoTypes.VOD;
                break;
        }

        Boolean isDvr = intent.getBooleanExtra("isDvr", false);

        if (isDvr) {
            config.dvr = true;
            config.windowDvr = 3600;
        }

        config.isDebug = true;

        isService = intent.getBooleanExtra("isService", false);
        container = findViewById(R.id.main_media_frame);
        player = new MediastreamPlayer(this, config, container);
        player.addPlayerCallback(new MediastreamPlayerCallback() {
            @Override
            public void onPlay() {

            }

            @Override
            public void onPause() {

            }

            @Override
            public void onReady() {

            }

            @Override
            public void onEnd() {

            }

            @Override
            public void onBuffering() {

            }

            @Override
            public void onError(String error) {

            }

            @Override
            public void onNext() {

            }

            @Override
            public void onPrevious() {

            }

            @Override
            public void onFullscreen() {

            }

            @Override
            public void offFullscreen() {

            }

            @Override
            public void onNewSourceAdded() {

            }

            @Override
            public void onLocalSourceAdded() {

            }

            @Override
            public void onAdPlay() {

            }

            @Override
            public void onAdPause() {

            }

            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdResume() {

            }

            @Override
            public void onAdEnded() {

            }

            @Override
            public void onAdError() {

            }

            @Override
            public void onAdBuffering() {

            }

            @Override
            public void onConfigChange(MediastreamMiniPlayerConfig config) {

            }

            @Override
            public void onCastAvailable(Boolean state) {

            }

            @Override
            public void onCastSessionStarting() {

            }

            @Override
            public void onCastSessionStarted() {

            }

            @Override
            public void onCastSessionStartFailed() {

            }

            @Override
            public void onCastSessionEnding() {

            }

            @Override
            public void onCastSessionEnded() {

            }

            @Override
            public void onCastSessionResuming() {

            }

            @Override
            public void onCastSessionResumed() {

            }

            @Override
            public void onCastSessionResumeFailed() {

            }

            @Override
            public void onCastSessionSuspended() {

            }

            @Override
            public void onPlaybackErrors(JSONObject error) {

            }

            @Override
            public void onEmbedErrors(JSONObject error) {

            }
        });

        if (isService) {
            startService(player);
        }
    }

    @Override
    public void onBackPressed() {
        player.releasePlayer();
        if (isService) {
            Intent serviceIntent = new Intent(this, MediastreamPlayerService.class);
            serviceIntent.setAction(getPackageName()+".action.stopforeground");
            try {
                ContextCompat.startForegroundService(this, serviceIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        startActivity(new Intent(Player.this, MainActivity.class));
    }

    void startService(MediastreamPlayer player) {
        MediastreamPlayerService.setupService(player);
        Intent serviceIntent = new Intent(this, MediastreamPlayerService.class);
        serviceIntent.setAction(getPackageName()+".action.startforeground");
        ContextCompat.startForegroundService(this, serviceIntent);
    }
}
