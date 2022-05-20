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

<<<<<<< HEAD
        Queue filaTeste = new Queue("fila-leonardo-souza", true, false, false);
=======
        Queue filaTeste = new Queue("fila-igor-gorini", true, false, false);
>>>>>>> a6ca68deb75465eccafd39a69217ad806e2220d2
        FanoutExchange fanoutExchangeTeste = new FanoutExchange("fanout-exchange-teste", true, false);
        Binding bindingTeste = new Binding(filaTeste.getName(), DestinationType.QUEUE, fanoutExchangeTeste.getName(), "", null);
        this.amqpAdmin.declareQueue(filaTeste);
        this.amqpAdmin.declareExchange(fanoutExchangeTeste);
        this.amqpAdmin.declareBinding(bindingTeste);

        //this.rabbitTemplate.convertAndSend(fanoutExchangeTeste.getName(), "", "message");

    }

<<<<<<< HEAD
    // @RabbitListener(queues = "fila-teste", ackMode = "AUTO")
    // private void consumerFilaTeste(Aluno aluno){
    //     try {
    //         Thread.sleep(15000);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     System.out.println(aluno);
    // }
=======
   /* @RabbitListener(queues = "fila-teste", ackMode = "AUTO")
    private void consumerFilaTeste(Aluno aluno) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(aluno);
    }*/

>>>>>>> a6ca68deb75465eccafd39a69217ad806e2220d2
}
