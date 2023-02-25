package com.codermonster.translator;

import com.intellij.ide.AppLifecycleListener;

/**
 * @Name: ApplicationListener
 * @Description:
 * @Author: Mr.Tong
 */
public class ApplicationListener implements AppLifecycleListener {
    @Override
    public void appStarted() {
        System.out.println("怪兽，我爱你");
    }
}
