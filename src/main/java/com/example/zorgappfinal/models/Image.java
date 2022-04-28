package com.example.zorgappfinal.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name ="images")
public class Image {
    @Id
    @GeneratedValue
    Long id;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    public byte[] content;

    @OneToOne
    @JoinColumn(name = "message_id")
    private Message message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getContent(){
        return content;
    }
    public void setContent(byte[] content){
        this.content = content;
    }

    public Message getMessage(){
        return message;
    }
    public void addToMessage (Message message){
        this.message = message;
    }
}
