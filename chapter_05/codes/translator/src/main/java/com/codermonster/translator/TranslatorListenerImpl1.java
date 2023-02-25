package com.codermonster.translator;

import com.intellij.openapi.project.Project;

/**
 * @Name: TranslatorListenerImpl1
 * @Description:
 * @Author: Mr.Tong
 */
public class TranslatorListenerImpl1 implements TranslatorManagerListener {
    @Override
    public void beforeTranslated(Project project) {
        System.out.println("第一个实现类：翻译之前");
    }

    @Override
    public void afterTranslated(Project project) {
        System.out.println("第一个实现类：翻译之后");
    }
}
