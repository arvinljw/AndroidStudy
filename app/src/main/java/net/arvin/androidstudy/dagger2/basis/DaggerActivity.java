package net.arvin.androidstudy.dagger2.basis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import net.arvin.androidstudy.R;
import net.arvin.androidstudy.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by arvinljw on 2018/8/1 16:46
 * Function：
 * Desc：
 */
public class DaggerActivity extends BaseActivity {

    TextView tvInfo;

    @Inject
    @UserModule.UserArvin
    User user;

    @Inject
    @UserModule.UserAda
    User userAda;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

        tvInfo = findViewById(R.id.tv_info);

//        DaggerDaggerActivityComponent.builder()
//                .userComponent(DaggerUserComponent.builder().build())
//                .appComponent(App.appComponent)
//                .build().inject(this);

        tvInfo.setText(String.format("%s %s", user.toString(), userAda.toString()));

    }
}
