package br.edu.unicesumar.pubsub.controller;

import com.rabbitmq.client.RpcClient.Response;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.amqp.core.Binding;

import br.edu.unicesumar.pubsub.dto.Message;

@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @RabbitListener(queues = Message.myUser, ackMode = "AUTO")
    private void listenerMessage(Message msg) {
        System.out.println(msg.getUser() + " : " + msg.getMessage());
    }

    @PostMapping("/group/{group}")
    private ResponseEntity<Void> createGroup(@PathVariable(name = "group") String groupName) {
        FanoutExchange fanoutExchangeTeste = new FanoutExchange("group-" + groupName, true, false);
        this.amqpAdmin.declareExchange(fanoutExchangeTeste);

        Binding binding = new Binding(Message.myUser, DestinationType.QUEUE, fanoutExchangeTeste.getName(), "", null);
        this.amqpAdmin.declareBinding(binding);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/group/join/{group}/{username}")
    public ResponseEntity<Void> postMethodName(@PathVariable(name = "username") String username, @PathVariable(name = "group") String groupName) {
        Binding binding = new Binding(username, DestinationType.QUEUE, "group-" + groupName, "", null);
        this.amqpAdmin.declareBinding(binding);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/fanout/{group}")
    public ResponseEntity<Void> sendFanoutMessage(@PathVariable(name = "group") String groupName, @RequestBody Message msg) {
        msg.setGrupo(groupName);
        this.rabbitTemplate.convertAndSend("group-"+groupName, "", msg);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/direct/{username}")
    public ResponseEntity<Void> sendDirectMessage(@PathVariable(name = "username") String username, @RequestBody Message msg) {
        this.rabbitTemplate.convertAndSend("msg-direct", username, msg);
        return ResponseEntity.ok().build();
    }

}
