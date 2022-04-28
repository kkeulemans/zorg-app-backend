package com.example.zorgappfinal.services;

import com.example.zorgappfinal.dto.MessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    List<MessageDto> getAllMessages();
    MessageDto getMessageById(Long id);
    MessageDto addMessage(MessageDto messageDto);
    void deleteMessage(Long id);
    void updateMessage(Long id, MessageDto message);
    void assignToAccount(Long id, Long accountId);
    void addAttachment(Long id, Long attachmentId);
}
