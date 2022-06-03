package br.edu.unicesumar.pubsub.amqp;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.unicesumar.pubsub.dto.Message;

@Component
public class RabbitStartup {

    @Autowired
    private AmqpAdmin amqpAdmin;


    @PostConstruct
    private void postConstruct() {
        Queue myQueue = new Queue(Message.myUser, true, false, false);
        DirectExchange msgDirect = new DirectExchange("msg-direct", true, false);
        Binding bindingMsgDirectMyQueue = new Binding(Message.myUser, DestinationType.QUEUE, msgDirect.getName(), Message.myUser, null);


        this.amqpAdmin.declareQueue(myQueue);
        this.amqpAdmin.declareExchange(msgDirect);
        this.amqpAdmin.declareBinding(bindingMsgDirectMyQueue);

        
    }

       /* 
        @Autowired
    private RabbitTemplate rabbitTemplate;
       
       Queue filaLogInfo = new Queue("info", true, false, false);
        Queue filaLogWarn = new Queue("warn", true, false, false);
        Queue filaLogError = new Queue("error", true, false, false);
        Queue filaLog = new Queue("log-geral", true, false, false);
       
        TopicExchange topicExchangeTeste = new TopicExchange("topic-exchange-teste", true, false);

        Binding bindingFilaLogInfo = new Binding(filaLogInfo.getName(), DestinationType.QUEUE, topicExchangeTeste.getName(), "#.info", null);
        Binding bindingFilaLogWarn = new Binding(filaLogWarn.getName(), DestinationType.QUEUE, topicExchangeTeste.getName(), "#.warn", null);
        Binding bindingFilaLogError = new Binding(filaLogError.getName(), DestinationType.QUEUE, topicExchangeTeste.getName(), "#.error", null);
        Binding bindingFilaLogGeral = new Binding(filaLog.getName(), DestinationType.QUEUE, topicExchangeTeste.getName(), "log.*", null);

        this.amqpAdmin.declareQueue(filaLogInfo);
        this.amqpAdmin.declareQueue(filaLogWarn);
        this.amqpAdmin.declareQueue(filaLogError);
        this.amqpAdmin.declareQueue(filaLog);
        this.amqpAdmin.declareExchange(topicExchangeTeste);
        this.amqpAdmin.declareBinding(bindingFilaLogInfo);
        this.amqpAdmin.declareBinding(bindingFilaLogWarn);
        this.amqpAdmin.declareBinding(bindingFilaLogError);
        this.amqpAdmin.declareBinding(bindingFilaLogGeral);

        this.rabbitTemplate.convertAndSend(topicExchangeTeste.getName(), "info", "message");

        // * -> uma palavra qualquer
        // # -> nenhuma ou varias palavra qualquer
        // . -> delimitador


       // Queue filaTeste = new Queue("fila-teste", true, false, false);
       // DirectExchange directExchangeTeste = new DirectExchange("direct-exchange-teste", true, false);
       // Binding bindingTeste = new Binding(filaTeste.getName(), DestinationType.QUEUE, directExchangeTeste.getName(), "igor.gorini", null);
       // this.amqpAdmin.declareQueue(filaTeste);
       // this.amqpAdmin.declareExchange(directExchangeTeste);
       // this.amqpAdmin.declareBinding(bindingTeste);
       // this.rabbitTemplate.convertAndSend(directExchangeTeste.getName(), "igor.gorini2", "message");


        //FanoutExchange fanoutExchangeTeste = new FanoutExchange("fanout-exchange-teste", true, false);
        //Binding bindingTeste = new Binding(filaTeste.getName(), DestinationType.QUEUE, fanoutExchangeTeste.getName(), "", null);
        //this.amqpAdmin.declareQueue(filaTeste);
        //this.amqpAdmin.declareExchange(fanoutExchangeTeste);
        //this.amqpAdmin.declareBinding(bindingTeste);

        //this.rabbitTemplate.convertAndSend(fanoutExchangeTeste.getName(), "", "message");
 @RabbitListener(queues = "fila-teste", ackMode = "AUTO")
    private void consumerFilaTeste(Aluno aluno) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(aluno);
    }*/

}
