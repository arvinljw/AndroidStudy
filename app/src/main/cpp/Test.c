/**
 * Created by arvinljw on 2018/8/30 20:00
 * Function：
 * Desc：
 */

#include<jni.h>
#include "string.h"
#include "Log.c"

//调用public void hello()方法
void Java_net_arvin_androidstudy_jni_CallJava_callVoid
        (JNIEnv *env, jobject instance) {
    jclass clazz = (*env)->FindClass(env, "net/arvin/androidstudy/jni/CallJava");
    jmethodID method = (*env)->GetMethodID(env, clazz, "hello", "()V");
    jobject object = (*env)->AllocObject(env, clazz);
    (*env)->CallVoidMethod(env, object, method);
}

//调用public int sum(int a, int b)方法
void Java_net_arvin_androidstudy_jni_CallJava_callJavaSum
        (JNIEnv *env, jobject instance) {
    jclass clazz = (*env)->FindClass(env, "net/arvin/androidstudy/jni/CallJava");
    jmethodID method = (*env)->GetMethodID(env, clazz, "sum", "(II)I");
    jobject object = (*env)->AllocObject(env, clazz);
    jint res = (*env)->CallIntMethod(env, object, method, 1, 2);
    LOGD("result = %d\n", res);
}

//调用public void sayHi(String msg)方法
JNIEXPORT void JNICALL Java_net_arvin_androidstudy_jni_CallJava_callSayHello
        (JNIEnv *env, jobject instance) {
    jclass clazz = (*env)->FindClass(env, "net/arvin/androidstudy/jni/CallJava");
    jmethodID method = (*env)->GetMethodID(env, clazz, "sayHi", "(Ljava/lang/String;)V");
    jobject object = (*env)->AllocObject(env, clazz);
    jstring msg = (*env)->NewStringUTF(env, "I am from c~");
    (*env)->CallVoidMethod(env, object, method, msg);
}

//调用public static void staticMethod()方法
JNIEXPORT void JNICALL Java_net_arvin_androidstudy_jni_CallJava_callStaticMethod
        (JNIEnv *env, jobject instance) {
    jclass clazz = (*env)->FindClass(env, "net/arvin/androidstudy/jni/CallJava");
    jmethodID method = (*env)->GetStaticMethodID(env, clazz, "staticMethod", "()V");
    (*env)->CallStaticVoidMethod(env, clazz, method);
}