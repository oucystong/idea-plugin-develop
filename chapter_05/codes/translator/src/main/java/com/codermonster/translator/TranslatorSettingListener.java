package com.codermonster.translator;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationAction;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import org.jetbrains.annotations.NotNull;

/**
 * @Name: TranslatorSettingListener
 * @Description: 项目打开时检查是否已经配置APPID和密钥，如果没有配置，提示进行配置
 * @Author: Mr.Tong
 */
public class TranslatorSettingListener implements ProjectManagerListener {


    @Override
    public void projectOpened(@NotNull Project project) {
//        System.out.println("-----");

        if (TranslatorSetting.getInstance().appID == null && TranslatorSetting.getInstance().securityKey == null) {// 没有配置APPID和密钥
            // 提示进行配置
            Notification notification = new Notification("怪兽翻译器", "请配置APPID和密钥", NotificationType.WARNING);
            // 在提示消息中，增加一个 Action，可以通过 Action 一步打开配置界面
            notification.addAction(new OpenTranslatorSettingAction());
            // 发送通知
            Notifications.Bus.notify(notification, project);
        }
    }


    static class OpenTranslatorSettingAction extends NotificationAction {

        OpenTranslatorSettingAction() {
            super("打开翻译插件配置界面");
        }

        @Override
        public void actionPerformed(@NotNull AnActionEvent e, @NotNull Notification notification) {
            // IntelliJ SDK 提供的一个工具类，可以通过配置项名字，直接显示对应的配置界面
            ShowSettingsUtil.getInstance().showSettingsDialog(e.getProject(), "CoderMonsterTranslator");
            notification.expire();
        }
    }
}
