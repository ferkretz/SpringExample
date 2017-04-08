package com.kferenc.jpf.configuration;

import java.util.Locale;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class CustomReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

    public String translate(String code) {
        return getMessage(code, new Object[]{}, "??" + code + "??", Locale.getDefault());
    }

    public String translatef(String code, Object... args) {
        return getMessage(code, args, "??" + code + "??", Locale.getDefault());
    }

}
