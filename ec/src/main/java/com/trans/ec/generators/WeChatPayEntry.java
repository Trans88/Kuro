package com.trans.ec.generators;

import com.trans.kuro_core.wechat.templates.WXPayEntryTemplate;
import com.trans.latte_annotations.PayEntryGenerator;

@PayEntryGenerator(
        packageName = "com.trans.ec",
        payEntryTemplate = WXPayEntryTemplate.class
)
public class WeChatPayEntry {
}
