package net.arvin.androidstudy.dagger2;

import android.content.SharedPreferences;

import net.arvin.androidstudy.App;
import net.arvin.androidstudy.dagger2.basis.UserComponent;
import net.arvin.androidstudy.dagger2.basis.UserModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by arvinljw on 2018/8/2 15:18
 * Function：
 * Desc：
 */
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class, AllActivitiesModule.class})
public interface AppComponent {
    void inject(App app);
}
