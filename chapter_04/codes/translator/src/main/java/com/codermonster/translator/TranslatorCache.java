package com.codermonster.translator;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Name: TranslatorCache
 * @Description:
 * @Author: Mr.Tong
 */
@State(name = "translator.cache", storages = {@Storage(value = "translator-cache.xml")})
public class TranslatorCache implements PersistentStateComponent<TranslatorCache> {

    public Map<String, String> cacheMap = new LinkedHashMap<>();

    // 获取反序列化后的结果-项目级别
    public static TranslatorCache getInstance(Project project) {
        return project.getService(TranslatorCache.class);
    }

    @Override
    public @Nullable TranslatorCache getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull TranslatorCache state) {
        if (state == null) {
            return;
        }
        // 拿到反序列化后的对象数据
        this.cacheMap = state.cacheMap;
    }
}
