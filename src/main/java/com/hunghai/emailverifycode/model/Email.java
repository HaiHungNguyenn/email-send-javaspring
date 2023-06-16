package com.hunghai.emailverifycode.model;

import java.util.HashMap;
import java.util.Map;

public class Email {
    private String from;
    private String to;
    private String subject;
    private String template;
    private Map<String, Object> properties;
    public Map<String, Object> getProperties() {

        return properties;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
