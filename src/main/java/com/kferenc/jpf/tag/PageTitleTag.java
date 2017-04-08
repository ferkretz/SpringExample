package com.kferenc.jpf.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PageTitleTag extends SimpleTagSupport {

    private String icon;
    private String message;

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (message == null || message.isEmpty()) {
            return;
        }

        JspWriter out = getJspContext().getOut();
            out.append("<div class=\"page-title\">");
            out.append("<div class=\"icon22 ").append(icon).append("\"></div>");
            out.append("<div class=\"text\">").append(message).append("</div>");
            out.append("</div>");
    }

}
