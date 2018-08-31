package net.arvin.androidstudy.dagger2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import net.arvin.androidstudy.R;
import net.arvin.androidstudy.dagger2.basis.User;
import net.arvin.androidstudy.dagger2.basis.UserModule;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by arvinljw on 2018/8/2 16:00
 * Function：
 * Desc：
 */
public class DaggerAndroidActivity extends AppCompatActivity {
    TextView tvInfo;

    @Inject
    User user;

    @Inject
    User user1;

    @Inject
    SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

        tvInfo = findViewById(R.id.tv_info);

        tvInfo.setText(String.format("%s %s %s", user.toString(), user1.toString(), sp.toString()));
    }
}
