package net.arvin.androidstudy;

import android.app.Activity;
import android.app.Application;

import net.arvin.androidstudy.dagger2.AppComponent;
import net.arvin.androidstudy.dagger2.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by arvinljw on 2018/8/2 15:17
 * Function：
 * Desc：
 */
public class App extends Application implements HasActivityInjector{

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.create().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
