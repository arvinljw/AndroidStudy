package net.arvin.androidstudy.jni;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

import net.arvin.androidstudy.R;

/**
 * Created by arvinljw on 2018/8/29 10:16
 * Function：
 * Desc：
 */
public class JniActivity extends AppCompatActivity {

    private static final String TAG = "JniActivity";
    private Hello jni = new Hello();
    private CallJava callJava = new CallJava();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        AppCompatButton btnJni = findViewById(R.id.btn_jni);
        btnJni.setText(jni.stringFromC());
        btnJni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jniAdd();
                jniStrcat();
                jniDealArray();
                jniCheckStr();
                jniCallVoid();
                jniCallJavaSum();
                jniCallSayHello();
                jniStaticMethod();
            }
        });
    }

    private void jniAdd() {
        Log.d(TAG, "1 + 2 = " + jni.add(1, 2));
    }

    private void jniStrcat() {
        String result = jni.sayHello("I am from Java");
        Log.d(TAG, result);
    }

    private void jniDealArray() {
        int[] array = {1, 2, 3, 4, 5};
        jni.increaseArray(array);
        for (int i = 0; i < array.length; i++) {
            Log.d(TAG, "array[" + i + "] = " + array[i]);
        }
    }

    private void jniCheckStr() {
        jni.checkStr("123456");
        Log.d(TAG, "input 123456,result is " + jni.checkStr("123456"));
        Log.d(TAG, "input 12345678,result is " + jni.checkStr("12345678"));
    }

    private void jniCallJavaSum() {
        callJava.callJavaSum();
    }

    private void jniCallVoid() {
        callJava.callVoid();
    }

    private void jniCallSayHello() {
        callJava.callSayHello();
    }

    private void jniStaticMethod() {
        callJava.callStaticMethod();
    }
}
