package com.codermonster.translator;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowManagerListener;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * @Name: InitTranslatorWindowListener
 * @Description:
 * @Author: Mr.Tong
 */
public class InitTranslatorWindowListener implements ToolWindowManagerListener {
    private final Project project;

    public InitTranslatorWindowListener(Project project) {
        this.project = project;
    }


/*    // 工具栏窗口被打开展示的时候
    @Override
    public void toolWindowShown(@NotNull ToolWindow toolWindow) {
        if (!toolWindow.getId().equals("CoderTranslator")) {
            return;
        }
        // 确定打开的时CoderTranslator窗口
        TranslatorCache.getInstance(project).cacheMap.forEach((s, s2) -> TranslatorToolsWindow.addNote(s, s2));
    }*/

    @Override
    public void toolWindowsRegistered(@NotNull List<String> ids, @NotNull ToolWindowManager toolWindowManager) {
        if (ids.contains("CoderTranslator")) {
            // 确定打开的时CoderTranslator窗口
            TranslatorCache.getInstance(project).cacheMap.forEach((s, s2) -> TranslatorToolsWindow.addNote(s, s2));
        }
    }
}
