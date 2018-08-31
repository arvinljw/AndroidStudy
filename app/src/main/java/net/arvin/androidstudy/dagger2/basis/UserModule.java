package net.arvin.androidstudy.dagger2.basis;

import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arvinljw on 2018/8/2 11:18
 * Function：
 * Desc：
 */
@Module
public class UserModule {
    @Provides
    @UserArvin
    User providerUser() {
        return new User("arvin");
    }

    @Provides
    @UserAda
    User providerAda() {
        return new User("Ada");
    }

    @Qualifier
    public @interface UserArvin{}
    @Qualifier
    public @interface UserAda{}
}
