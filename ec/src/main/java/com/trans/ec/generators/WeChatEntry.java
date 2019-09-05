package com.trans.ec.generators;

import com.trans.kuro_core.wechat.templates.AppRegisterTemplate;
import com.trans.kuro_core.wechat.templates.WXEntryTemplate;
import com.trans.latte_annotations.EntryGenerator;
@EntryGenerator(
        packageName = "com.trans.ec",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
