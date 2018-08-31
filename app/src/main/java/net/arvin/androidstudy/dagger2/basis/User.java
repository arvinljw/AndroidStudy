package net.arvin.androidstudy.dagger2.basis;

import javax.inject.Inject;
import javax.inject.Qualifier;

/**
 * Created by arvinljw on 2018/8/2 11:08
 * Function：
 * Desc：
 */
public class User {
    String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

}

