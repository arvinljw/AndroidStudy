package net.arvin.androidstudy.dagger2;

import net.arvin.androidstudy.dagger2.modules.AndroidModule;

import javax.inject.Scope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by arvinljw on 2018/8/2 16:12
 * Function：
 * Desc：
 */
@Module
public abstract class AllActivitiesModule {

    @ContributesAndroidInjector(modules = AndroidModule.class)
    abstract DaggerAndroidActivity contributeDaggerAndroidActivityInjector();

}
