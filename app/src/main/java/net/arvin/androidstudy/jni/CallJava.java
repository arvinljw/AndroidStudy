package net.arvin.androidstudy.jni;

import android.util.Log;

/**
 * Created by arvinljw on 2018/8/31 09:46
 * Function：
 * Desc：
 */
public class CallJava {
    static {
        System.loadLibrary("Hello");
    }

    private static final String TAG = "CallJava";

    //调用无参，无返回函数
    public native void callVoid();

    //调用有参，有返回函数
    public native void callJavaSum();

    //调用有参数，无返回函数
    public native void callSayHello();

    //调用静态方法
    public native void callStaticMethod();

    private void hello() {
        Log.d(TAG, "Java的hello方法");
    }

    public int sum(int a, int b) {
        Log.d(TAG, "Java的sum方法");
        return a + b;
    }

    public void sayHi(String msg) {
        Log.d(TAG, "Java的sayHello方法 - c传入的字符串 = " + msg);
    }

    public static void staticMethod() {
        Log.d(TAG, "Java的staticMethod静态方法");
    }

}
