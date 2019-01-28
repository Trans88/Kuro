package com.trans.latte_ec.icon;

import com.joanzapata.iconify.Icon;

public enum  EcIcons implements Icon {
    icon_like('\ue669'),
    icon_notification('\ue66b');
    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return  name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }

}
