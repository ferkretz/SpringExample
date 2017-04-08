package com.kferenc.jpf.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class LogTag extends SimpleTagSupport {

    private String type = "message";
    private String message = "";
    private String hide = "";

    public void setType(String type) {
        this.type = type;
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
        if ("information".equals(type) || "warning".equals(type) || "error".equals(type)) {
            out.append("<div class='log ").append(type).append(hide).append("'>");
            out.append("<div class='icon dialog-").append(type).append("'></div>");
            out.append("<div class='text'>").append(message).append("</div>");
            out.append("</div>");
        }
    }

}
