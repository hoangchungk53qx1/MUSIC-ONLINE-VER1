package com.bhsoft.musiconline.utils;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.bhsoft.musiconline.callback.OnPlayingListener;

import java.io.IOException;

public class AudioPlayerUtils {

    private static AudioPlayerUtils audioPlayerUtils;
    private MediaPlayer mediaPlayer;
    private OnPlayingListener onPlayingListener;
    public static AudioPlayerUtils getInstance(){
        if(audioPlayerUtils==null){
            audioPlayerUtils = new AudioPlayerUtils();
        }
        return audioPlayerUtils;

    }

    public void play(String path, final OnPlayingListener onPlayingListener1){
        if (mediaPlayer == null) {
            onPlayingListener = onPlayingListener1;
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(path);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        if (onPlayingListener != null){
                            onPlayingListener.onPlay();
                        }
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        clear();
                        if (onPlayingListener != null){
                            onPlayingListener.onComplete();
                        }
                    }
                });
                mediaPlayer.prepare();
            } catch (IOException e) {
                Log.d("ghetrer4ger4", e.toString());
            }
            return;
        }

        clear();
        play(path,onPlayingListener);
    }

    public void clear() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void pause(){
        if (mediaPlayer != null){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                onPlayingListener.onPause();
            }
        }
    }

    public void resume(){
        if (mediaPlayer != null){
            if (!mediaPlayer.isPlaying()){
                mediaPlayer.start();
                onPlayingListener.onResume();
            }
        }
    }
}
