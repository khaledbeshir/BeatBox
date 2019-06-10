package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed Amr on 5/2/2019.
 */

public class BeatBox {
    private static final String TAG = "beatbox";
    private static final String  SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;
    private AssetManager mAssetManager;
    private SoundPool mSoundPool;
    private List<Sound> mSounds= new ArrayList<>();

    public BeatBox(Context context){
        mAssetManager = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS , AudioManager.STREAM_MUSIC, 0);
        loadsounds();
    }

    public void loadsounds (){
        String [] soundNames;
        try{
            soundNames = mAssetManager.list(SOUNDS_FOLDER);
            Log.i(TAG ,"sounds " + soundNames.length + "found");
        } catch (IOException e) {
            Log.e(TAG , "couldent list assets" , e);
            return;
        }

        for(String filename : soundNames){
            try {
                String AssetPath = SOUNDS_FOLDER + "/" + filename ;
                Sound sound = new Sound(AssetPath);
                Load(sound);
                mSounds.add(sound);
            } catch (IOException ioe) {
               Log.e(TAG , "could not load sound" + filename , ioe);
            }
        }
    }

    private void Load(Sound sound) throws IOException {
        AssetFileDescriptor assetfd =mAssetManager.openFd(sound.gettAssetPath()) ;
        int soundId = mSoundPool.load(assetfd , 1);
        sound.SetSoundId(soundId);
    }

    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if(soundId == null){
            return;
        }
        mSoundPool.play(soundId , 1.0f , 1.0f , 1,0,1.0f);
    }

    public void release(){
        mSoundPool.release();
    }

    public List<Sound> getSounds (){
        return mSounds;
    }


}


