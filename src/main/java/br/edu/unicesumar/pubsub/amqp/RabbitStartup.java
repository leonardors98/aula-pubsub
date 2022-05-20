package br.edu.unicesumar.pubsub.amqp;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.unicesumar.pubsub.domain.Aluno;

@Component
public class RabbitStartup {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    private void adiciona() {


        Queue filaTeste = new Queue("fila-leonardo-souza", true, false, false);
        FanoutExchange fanoutExchangeTeste = new FanoutExchange("fanout-exchange-teste", true, false);
        Binding bindingTeste = new Binding(filaTeste.getName(), DestinationType.QUEUE, fanoutExchangeTeste.getName(), "", null);
        this.amqpAdmin.declareQueue(filaTeste);
        this.amqpAdmin.declareExchange(fanoutExchangeTeste);
        this.amqpAdmin.declareBinding(bindingTeste);

        //this.rabbitTemplate.convertAndSend(fanoutExchangeTeste.getName(), "", "message");

    }

    // @RabbitListener(queues = "fila-teste", ackMode = "AUTO")
    // private void consumerFilaTeste(Aluno aluno){
    //     try {
    //         Thread.sleep(15000);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     System.out.println(aluno);
    // }
}
