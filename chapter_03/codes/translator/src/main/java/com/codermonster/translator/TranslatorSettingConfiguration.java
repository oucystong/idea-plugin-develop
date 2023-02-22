package com.codermonster.translator;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @Name: TranslatorSettingConfiguration
 * @Description:
 * @Author: Mr.Tong
 */
public class TranslatorSettingConfiguration implements Configurable {

    private final JComponent component;
    private final JTextField appID;
    private final JTextField securityKey;
    private final static String appIDHint = "请输入APPID";
    private final static String securityKeyHint = "请输入密钥";

    // 构造器，IDE 在初始化我们插件的时候，会实例化拓展点对象，而实例化时只能通过无参构造器创建对象。
    public TranslatorSettingConfiguration() {
        this.component = new JPanel();
        this.component.setLayout(new GridLayout(15, 1));

        // 创建appID、securityKey文本框
        this.appID = new JTextField();
        this.securityKey = new JTextField();

        //设置输入框提示语
        this.appID.setText(appIDHint);
        this.appID.setForeground(JBColor.GRAY);
        this.appID.addFocusListener(new TextFieldListener(this.appID, appIDHint));

        this.securityKey.setText(securityKeyHint);
        this.securityKey.setForeground(JBColor.GRAY);
        this.securityKey.addFocusListener(new TextFieldListener(this.securityKey, securityKeyHint));

        this.component.add(this.appID);
        this.component.add(this.securityKey);
    }


    // 获取配置在 Settings/Preferences 中显示的名字
    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "CoderMonsterTranslator";
    }

    // 基于Swing设计配置界面的UI
    @Override
    public @Nullable JComponent createComponent() {
        return component;
    }

    // 提供给 IDE 判断配置是否发生变更，若返回 true，则配置界面中的 apply 按钮可点击
    @Override
    public boolean isModified() {
        return true;
    }

    // 当在配置页面点击 apply 或者 ok 按钮时，该方法会被调用
    @Override
    public void apply() throws ConfigurationException {
        TranslatorUtils.appid = appID.getText();
        TranslatorUtils.securityKey = securityKey.getText();
    }


    static class TextFieldListener implements FocusListener {

        private final String defaultHint;
        private final JTextField textField;

        public TextFieldListener(JTextField textField, String defaultHint) {
            this.defaultHint = defaultHint;
            this.textField = textField;
        }

        // 获得焦点
        @Override
        public void focusGained(FocusEvent e) {
            // 清空提示语，设置为黑色字体
            if (textField.getText().equals(defaultHint)) {
                textField.setText("");
                textField.setForeground(JBColor.BLACK);
            }
        }

        // 失去焦点
        @Override
        public void focusLost(FocusEvent e) {
            // 如果内容为空，设置提示语
            if (textField.getText().equals("")) {
                textField.setText(defaultHint);
                textField.setForeground(JBColor.GRAY);
            }
        }
    }
}
