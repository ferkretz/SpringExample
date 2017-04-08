package com.kferenc.jpf.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ButtonTag  extends SimpleTagSupport {

    private String classes;
    private String disabled;
    private String selected;
    private String icon;
    private String id;
    private String name;
    private String type;
    private String value;

    public String getClasses() {
        return classes;
    }

    public String getDisabled() {
        return disabled;
    }

    public String getSelected() {
        return selected;
    }

    public String getIcon() {
        return icon;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.append("<button");
        if (type != null) out.append(" type=\"").append(type).append("\"");
        if (id != null) out.append(" id=\"").append(id).append("\"");
        if (name != null) out.append(" name=\"").append(name).append("\"");
        if (classes != null) out.append(" class=\"").append(classes).append("\"");
        if (disabled != null) out.append(" disabled=\"").append(disabled).append("\"");
        out.append(">");
        if (icon != null) out.append("<div class=\"icon ").append(icon).append("\">").append("</div>");
        if (value != null) out.append("<div class=\"text\">").append(value).append("</div>");
        out.append("</button>");
        out.flush();
    
    }
}
