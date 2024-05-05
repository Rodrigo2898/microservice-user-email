package com.microservice.user.producer;

import com.microservice.user.dto.EmailDTO;
import com.microservice.user.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// essa classe realiza a publicação das menssagens para o broker
@Component
public class UserProducer {
    private final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // exchange do tipo default: chave routing key é do nome da fila
    @Value("${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User user) {
        var emailDTO = new EmailDTO();
        emailDTO.setUserId(user.getId());
        emailDTO.setEmailTo(user.getEmail());
        emailDTO.setSubject("Email cadastrado com sucesso");
        emailDTO.setText(user.getName() + ", seja bem-vindo(a)!\n Agradecemos o seu cadastro");

        rabbitTemplate.convertAndSend("", routingKey, emailDTO);
    }
}
