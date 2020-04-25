package pl.zielinska.trashAlert.utils;


import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.beans.PropertyEditorSupport;

public class StringEscapeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        if (text == null) {
            setValue(null);
        }
        else {
            String safe = Jsoup.clean(text, Whitelist.simpleText());
            setValue(safe);
        }
    }
}
