package com.example.zorgappfinal.controller;

import com.example.zorgappfinal.models.Image;
import com.example.zorgappfinal.models.Message;
import com.example.zorgappfinal.repositories.ImageRepository;
import com.example.zorgappfinal.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageController {
    @Autowired
    ImageRepository imgRepos;
    @Autowired
    MessageRepository messageRepository;

    @PostMapping("/messages/{id}/attachment")
    public String upload(@RequestBody MultipartFile file, @PathVariable Long id) {
        Image img = new Image();
        Message message = messageRepository.findById(id).get();
        try {
            img.content = file.getBytes();
            img.addToMessage(message);
            message.setAttachment(img);
        }
        catch (IOException iex) {
            return "Error while uploading image...";
        }

        imgRepos.save(img);
        messageRepository.save(message);
        return "Image uploaded";
    }

    @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] download(@PathVariable Long id) {
        Image img = imgRepos.findById(id).get();
        return img.content;
    }

    @DeleteMapping("/messages/{id}/{attachmentId}")
    public void deleteAttachment (@PathVariable("id") Long messageId,@PathVariable("attachmentId") Long id ){
        Message message= messageRepository.findById(messageId).get();
        message.setAttachment(null);
        messageRepository.save(message);
        imgRepos.deleteById(id);
    };

}
