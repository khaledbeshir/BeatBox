package com.bignerdranch.android.beatbox;

/**
 * Created by Mohamed Amr on 5/3/2019.
 */

public class Sound {
    private String mName;
    private String mAssetPath;
    private Integer mSoundId;

    public Sound(String AssetPath){
        mAssetPath = AssetPath ;
        String [] components = mAssetPath.split("/");
        String soundname = components[components.length - 1];
        mName = soundname.replace(".wav" , "");
    }

    public String gettAssetPath (){
        return mAssetPath;
    }

    public String getName(){
        return mName;
    }

    public void SetSoundId(Integer SoundId){
        mSoundId = SoundId;
    }

    public Integer getSoundId (){
        return mSoundId;
    }
}
