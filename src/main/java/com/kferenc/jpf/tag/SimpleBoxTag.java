package com.kferenc.jpf.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class SimpleBoxTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    private String title;
    private boolean closable;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClosable(String closable) {
        this.closable = Boolean.parseBoolean(closable);
    }

    @Override
    public int doAfterBody() throws JspException {
        try {
            BodyContent bodycontent = getBodyContent();
            String body = bodycontent.getString();
            JspWriter out = bodycontent.getEnclosingWriter();

            out.append("<div class='simple-box'>");
            out.append("<div class='title'>")
                    .append("<span class='collapse-trigger'></span>")
                    .append(title)
                    .append("</div>");
            out.append("<div class='content'>").append(body).append("</div>");
            out.append("</div>");

        } catch (IOException ioe) {
            throw new JspException("Error:" + ioe.getMessage());
        }
        return SKIP_BODY;
    }

}
