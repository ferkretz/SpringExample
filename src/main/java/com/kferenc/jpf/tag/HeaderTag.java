package com.kferenc.jpf.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;

public class HeaderTag extends BodyTagSupport {

    private static final long serialVersionUID = 1L;

    String title;
    String cssPath;
    String scriptsPath;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCssPath(String cssPath) {
        this.cssPath = cssPath;
    }

    public void setScriptsPath(String scriptsPath) {
        this.scriptsPath = scriptsPath;
    }

    @Override
    public int doAfterBody() throws JspException {
        try {
            BodyContent bodycontent = getBodyContent();
            String body = bodycontent.getString();
            JspWriter out = bodycontent.getEnclosingWriter();

            out.append("<head>")
                    .append("<title>").append(title).append("</title>")
                    .append("<meta charset='UTF-8' />")
                    .append("<meta name='viewport' content='width=device-width, initial-scale=1.0' />")
                    .append("<meta name='description' content='Java Pinguin Forum' />")
                    .append("<meta name='author' content='Ferenc Kretz' />")
                    .append("<link type='text/css' rel='stylesheet' href='").append(cssPath).append("/icons.css' />")
                    .append("<link type='text/css' rel='stylesheet' href='").append(cssPath).append("/nobar-wrapper.css' />")
                    .append("<link type='text/css' rel='stylesheet' href='").append(cssPath).append("/components.css' />")
                    .append("<link type='text/css' rel='stylesheet' href='").append(cssPath).append("/page.css' />")
                    .append("<link type='text/css' rel='stylesheet' href='").append(cssPath).append("/controls.css' />")
                    .append("<link type='text/css' rel='stylesheet' href='").append(cssPath).append("/form.css' />")
                    .append("<script src='").append(scriptsPath).append("/jquery-2.2.4.min.js'>//</script>")
                    .append("<script src='").append(scriptsPath).append("/scripts.js'>//</script>")
                    .append(body);
            out.append("</head>");
        } catch (IOException ioe) {
            throw new JspException("Error:" + ioe.getMessage());
        }
        return SKIP_BODY;
    }

}
