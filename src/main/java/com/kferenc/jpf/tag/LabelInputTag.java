package com.kferenc.jpf.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class LabelInputTag extends SimpleTagSupport {

    private String type;
    private String label;
    private String path;
    private String value;
    private String required;
    private String size = "40";

    public void setType(String type) {
        this.type = type;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        if (type == null) {
            type = "text";
        }

        if ("checkbox".equals(type)) {
            out.append("<div class='label-input checkbox'>");
            out.append("<input id='").append(path);
            out.append("' name='").append(path);
            out.append("' type='").append(type);
            if (value != null) {
                out.append("' value='").append(value);
            }
            if (required != null) {
                out.append("' required='").append(required);
            }
            out.append("' size='").append(size).append("' />");
            out.append("<label>").append(label).append("</label>");
            out.append("</div>");

            return;
        }

        out.append("<div class='label-input'>");
        out.append("<label>").append(label).append("</label>");
        out.append("<input id='").append(path);
        out.append("' name='").append(path);
        out.append("' type='").append(type);
        if (value != null) {
            out.append("' value='").append(value);
        }
        if (required != null) {
            out.append("' required='").append(required);
        }
        out.append("' size='").append(size).append("' />");
        out.append("</div>");
    }

}
