package com.codermonster.helloworldplugin;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @Name: HelloWorldAction
 * @Description:
 * @Author: Mr.Tong
 */
public class HelloWorldAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        System.out.println("功能被触发");
        // 在IDEA中发送系统通知
        Notifications.Bus.notify(new Notification("HelloWorldPlugin", "欢迎来到插件世界！", NotificationType.INFORMATION), e.getProject());
        // Notifications.Bus.notify方法接收两个参数，第一个参数是一个通知，第二个参数是项目
        // Notification构造器包括三个参数
        // 1、Group信息
        // 2、通知信息
        // 3、通知消息的类型
    }
}
