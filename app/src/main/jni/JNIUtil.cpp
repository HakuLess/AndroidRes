//
// Created by 马双飞 on 16/3/1.
//

#include "less_haku_androidres_util_JNIUtil.h"
#include <stdio.h>
#include <string.h>
#include <jni.h>

JNIEXPORT jstring JNICALL Java_less_haku_androidres_util_JNIUtil_getStringFormC
        (JNIEnv * env, jclass obj){
    return env->NewStringUTF("这里是C++代码");
}

JNIEXPORT jstring JNICALL Java_less_haku_androidres_util_JNIUtil_getStringWithPara
        (JNIEnv *env, jobject obj, jstring prompt){
//    const char* str;
//    str = env->GetStringUTFChars(prompt, false);
//    env->ReleaseStringUTFChars(prompt, str);
//
//    char* tmpstr = "return string succeeded";
//    jstring rtstr = env->NewStringUTF(tmpstr);

    const char *c_str = NULL;
    char buff[128] = {0};
    jboolean isCopy;    // 返回JNI_TRUE表示原字符串的拷贝，返回JNI_FALSE表示返回原字符串的指针
    c_str = env->GetStringUTFChars(prompt, &isCopy);

    if(c_str == NULL)
    {
        return NULL;
    }
    env->ReleaseStringUTFChars(prompt, c_str);
    return env->NewStringUTF(buff);
//    return rtstr;
}
