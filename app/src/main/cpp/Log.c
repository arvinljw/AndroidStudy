/**
 * Created by arvinljw on 2018/8/31 12:11
 * Function：
 * Desc：
 */
#include "android/log.h"

#define LOG_TAG "JNI_TEST"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)