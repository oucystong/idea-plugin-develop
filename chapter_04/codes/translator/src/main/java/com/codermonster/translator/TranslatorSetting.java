package com.codermonster.translator;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @Name: TranslatorSetting
 * @Description:
 * @Author: Mr.Tong
 */
@State(name = "translator", storages = {@Storage(value = "translator.xml")})
public class TranslatorSetting implements PersistentStateComponent<TranslatorSetting> {


    public static TranslatorSetting getInstance() {
        return ApplicationManager.getApplication().getService(TranslatorSetting.class);
    }

    public String appID;
    public String securityKey;

    // IDE获取状态数据，即配置数据，通过XML序列化实现持久化过程
    // 点击OK的时候会执行
    @Override
    public @Nullable TranslatorSetting getState() {
        // 设置完APPID和密钥之后，使其可以直接使用，否则只能重启才可以使用
        TranslatorUtils.appid = this.appID;
        TranslatorUtils.securityKey = this.securityKey;
        return this;
    }

    // IDE重启的时候通过XML反序列化实现加载配置数据的过程
    @Override
    public void loadState(@NotNull TranslatorSetting state) {
        this.appID = state.appID;
        this.securityKey = state.securityKey;
    }
}
