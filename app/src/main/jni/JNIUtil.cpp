//
// Created by 马双飞 on 16/3/1.
//

#include "less_haku_androidres_util_JNIUtil.h"

JNIEXPORT jstring JNICALL Java_less_haku_androidres_util_JNIUtil_getStringFormC
        (JNIEnv * env, jclass obj){
    return env->NewStringUTF("这里是C++代码");
}
