package net.arvin.androidstudy.dagger2.basis;

import net.arvin.androidstudy.dagger2.AppComponent;

import dagger.Component;

/**
 * Created by arvinljw on 2018/8/2 15:54
 * Function：
 * Desc：
 */
@Component(dependencies = {UserComponent.class})
public interface DaggerActivityComponent {
    void inject(DaggerActivity daggerActivity);
}
