package net.arvin.androidstudy.dagger2.modules;

import android.content.Context;
import android.content.SharedPreferences;

import net.arvin.androidstudy.dagger2.DaggerAndroidActivity;
import net.arvin.androidstudy.dagger2.basis.User;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arvinljw on 2018/8/2 16:17
 * Function：
 * Desc：
 */
@Module
public class AndroidModule {

    @Provides
    String provideName() {
        return DaggerAndroidActivity.class.getName();
    }

    @Provides
    SharedPreferences getSp(DaggerAndroidActivity activity){
        return activity.getSharedPreferences("def", Context.MODE_PRIVATE);
    }

    @Provides
    User provideUser() {
        return new User("arvin");
    }
}
