package com.example.zorgappfinal.services;

import com.example.zorgappfinal.exceptions.RecordNotFoundException;
import com.example.zorgappfinal.dto.MessageDto;
import com.example.zorgappfinal.models.Account;
import com.example.zorgappfinal.models.Image;
import com.example.zorgappfinal.models.Message;
import com.example.zorgappfinal.repositories.AccountRepository;
import com.example.zorgappfinal.repositories.ImageRepository;
import com.example.zorgappfinal.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    AccountRepository accountRepository;
    @Override
    public List<MessageDto> getAllMessages() {
        List<Message> messageList = messageRepository.findAll();
        List<MessageDto> messageDtoList = new ArrayList<>();


        for (Message message : messageList) {
            MessageDto dto = transferToDto(message);
            messageDtoList.add(dto);
        }
        return messageDtoList;
    }

    @Override
    public MessageDto getMessageById(Long id) {
        Message message = messageRepository.getById(id);
        MessageDto messageDto = transferToDto(message);
        return messageDto;
    }

    @Override
    public MessageDto addMessage(MessageDto messageDto) {
        Message messageToBeAdded = transferToMessage(messageDto);
        messageRepository.save(messageToBeAdded);
        messageDto.setId(messageToBeAdded.getId());
        return messageDto;
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void updateMessage(Long id, MessageDto message) {
        message.setId(id);
        if (messageRepository.findById(id).isPresent()) {
            Message storedMessage = messageRepository.findById(id).get();
            storedMessage.setId(message.getId());
            storedMessage.setBody(message.getBody());
            storedMessage.setTitle(message.getTitle());
            messageRepository.save(storedMessage);

        } else {
            throw new RecordNotFoundException("No message found");
        }

    }

    @Override
    public void assignToAccount(Long id, Long accountId) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        Optional<Account> optionalUser = accountRepository.findById(accountId);
        if(optionalMessage.isPresent() && optionalUser.isPresent()){
            var message = optionalMessage.get();
            var user = optionalUser.get();

            message.addUser(user);
            messageRepository.save(message);

        }
        else {
            throw new RecordNotFoundException("User or Message not found");
        }
    }


    public MessageDto transferToDto(Message message) {
        var dto = new MessageDto();
        dto.setId(message.getId());
        dto.setBody(message.getBody());
        dto.setTitle(message.getTitle());
        return dto;
    }

    public Message transferToMessage(MessageDto messageDto) {
        var message = new Message();
        message.setId(messageDto.getId());
        message.setBody(messageDto.getBody());
        message.setTitle(messageDto.getTitle());
        return message;
    }
    public void addAttachment (Long id, Long attachmentId){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        Optional<Image> optionalImage = imageRepository.findById(attachmentId);

        if(optionalMessage.isPresent() && optionalImage.isPresent()){
            var message = optionalMessage.get();
            var image = optionalImage.get();

            message.setAttachment(image);
            messageRepository.save(message);

        }
        else {
            throw new RecordNotFoundException("Client or Message not found");
        }
    }

}


