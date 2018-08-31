package net.arvin.androidstudy.jni;

/**
 * Created by arvinljw on 2018/8/28 18:52
 * Function：
 * Desc：
 */
public class Hello {
    static {
        System.loadLibrary("Hello");
    }

    public native String stringFromC();

    public native int add(int a, int b);

    //传入一个字符串，拼接一段字符串后返回
    public native String sayHello(String msg);

    public native void increaseArray(int[] arr);

    //如果是c中要求的就返回200，否则就返回400
    public native int checkStr(String str);

}
