#include<jni.h>
#include<string.h>
#include "stdlib.h"

jstring Java_net_arvin_androidstudy_jni_Hello_stringFromC(JNIEnv *env, jobject instance) {
    char *str = "I am from c!";
    return (*env)->NewStringUTF(env, str);
}

jint Java_net_arvin_androidstudy_jni_Hello_add(JNIEnv *env, jobject instance,
                                               jint a, jint b) {
    return a + b;
}

jstring Java_net_arvin_androidstudy_jni_Hello_sayHello(JNIEnv *env, jobject instance,
                                                       jstring msg) {
    char *fromJava = (char *) (*env)->GetStringUTFChars(env, msg, JNI_FALSE);
    char *fromC = " add I am from C~";
    char *result = (char *) malloc(strlen(fromJava) + strlen(fromC));
    strcpy(result, fromJava);
    strcat(result, fromC);
    return (*env)->NewStringUTF(env, result);
}

void Java_net_arvin_androidstudy_jni_Hello_increaseArray
        (JNIEnv *env, jobject instance, jintArray arr) {
    jsize length = (*env)->GetArrayLength(env, arr);
    jint *elements = (*env)->GetIntArrayElements(env, arr, JNI_FALSE);
    for (int i = 0; i < length; i++) {
        elements[i] += 10;
    }
    (*env)->ReleaseIntArrayElements(env, arr, elements, 0);
}

jint Java_net_arvin_androidstudy_jni_Hello_checkStr
        (JNIEnv *env, jobject instance, jstring jstr) {
    char *input = (char *) (*env)->GetStringUTFChars(env, jstr, JNI_FALSE);
    char *real = "123456";
    return strcmp(input, real) == 0 ? 200 : 400;
}



