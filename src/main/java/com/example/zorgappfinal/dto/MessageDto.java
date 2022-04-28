package com.example.zorgappfinal.dto;

public class MessageDto {

    private Long id;
    private String title;
    private String body;
    private ImageDto attachment;



    public Long getId() {
        return id;
    }


    public String getTitle(){
        return title;
    }
    public String getBody(){
        return body;
    }

    public ImageDto getAttachment(){
        return attachment;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String setTitle( String title){
        this.title = title;
        return title;
    }
    public String setBody( String body){
        this.body = body;
        return body;
    }

    public ImageDto setAttachment( ImageDto attachment){
        this.attachment = attachment;
        return attachment;
    }

}
