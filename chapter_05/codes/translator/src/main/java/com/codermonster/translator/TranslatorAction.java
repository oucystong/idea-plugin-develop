package com.codermonster.translator;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.util.messages.MessageBus;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.util.Map;

/**
 * @Name: TranslatorAction
 * @Description:
 * @Author: Mr.Tong
 */
public class TranslatorAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // 非法判断
        if (TranslatorUtils.appid == null || TranslatorUtils.securityKey == null) {
            Notifications.Bus.notify(new Notification("CoderMonsterTranslator", "怪兽翻译器", "请先设置APPID和密钥", NotificationType.ERROR), e.getProject());
            return;
        }

        MessageBus messageBus = e.getProject().getMessageBus();
        TranslatorManagerListener publisher = messageBus.syncPublisher(TranslatorManagerListener.TRANSLATE_TOPIC);
        // 翻译之前
        publisher.beforeTranslated(e.getProject());

        // 获得翻译缓存数据
        Map<String, String> cacheMap = TranslatorCache.getInstance(e.getProject()).cacheMap;

        Editor editor = e.getData(CommonDataKeys.EDITOR);
        String text = editor.getSelectionModel().getSelectedText();

        String transResult = null;
        // 查询缓存
        if (cacheMap.containsKey(text)) {
            transResult = cacheMap.get(text);
        } else {
            // 通过API接口进行翻译
            transResult = TranslatorUtils.getTransResult(text, "auto", "zh");
            // 将结果进行缓存
            cacheMap.put(text, transResult);
        }

        Notifications.Bus.notify(new Notification("CoderMonsterTranslator", "怪兽翻译器", transResult, NotificationType.INFORMATION), e.getProject());

        // 翻译之后
        publisher.afterTranslated(e.getProject());

        // 在工具栏窗口中显示-去重
        JTable table = TranslatorToolsWindow.getTable();
        int rowCount = table.getModel().getRowCount();
//        System.out.println(rowCount);
        for (int i = 0; i < rowCount; i++) {
            String value = (String) table.getModel().getValueAt(i, 0);
//            System.out.println(value);
            if (text.equals(value)) {
                return;
            }
        }
        TranslatorToolsWindow.addNote(text, transResult);
    }
}
