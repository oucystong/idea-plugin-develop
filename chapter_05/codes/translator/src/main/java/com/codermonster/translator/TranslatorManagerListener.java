package com.codermonster.translator;

import com.intellij.openapi.project.Project;
import com.intellij.util.messages.Topic;

import java.util.EventListener;

/**
 * @Name: TranslatorManagerListener
 * @Description: 自定义事件
 * @Author: Mr.Tong
 */
public interface TranslatorManagerListener extends EventListener {


    // 定义 topic
    Topic<TranslatorManagerListener> TRANSLATE_TOPIC = Topic.create("translator", TranslatorManagerListener.class);

    // 翻译之前
    default void beforeTranslated(Project project) {
    }

    // 翻译之后
    default void afterTranslated(Project project) {
    }

}
