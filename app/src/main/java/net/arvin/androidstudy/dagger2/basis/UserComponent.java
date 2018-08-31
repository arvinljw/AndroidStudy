package net.arvin.androidstudy.dagger2.basis;

import dagger.Component;

/**
 * Created by arvinljw on 2018/8/2 11:09
 * Function：
 * Desc：
 */
@Component(modules = UserModule.class)
public interface UserComponent {
    @UserModule.UserArvin
    User getUser();

    @UserModule.UserAda
    User getUserAda();
}
