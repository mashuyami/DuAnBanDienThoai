package com.spring.services;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MailModel {
    private String form = "Vũ Minh <vu122580@gmail.com>";
    private String to;
    private String subject;
    private String body;
    private List<String> cc = new ArrayList<>();
    private List<String> bcc = new ArrayList<>();
    private List<File> files = new ArrayList<>();

    public MailModel(String to, String subject, String body) {
        super();
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

}
