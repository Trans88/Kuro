package com.trans.ec.generators;

import com.trans.kuro_core.wechat.templates.AppRegisterTemplate;
import com.trans.latte_annotations.AppRegisterGenerator;

@AppRegisterGenerator(
        packageName = "com.trans.ec",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {

}
