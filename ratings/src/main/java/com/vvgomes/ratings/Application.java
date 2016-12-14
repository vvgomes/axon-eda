package com.vvgomes.ratings;

import com.rabbitmq.client.Channel;
import org.axonframework.amqp.eventhandling.DefaultAMQPMessageConverter;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

  @Bean
  public SpringAMQPMessageSource ratingsMessageSource(Serializer serializer) {
    return new SpringAMQPMessageSource(new DefaultAMQPMessageConverter(serializer)) {
      @Override
      @RabbitListener(queues = "UsersEvents")
      public void onMessage(Message message, Channel channel) throws Exception {
        super.onMessage(message, channel);
      }
    };
  }

	@Bean
	public Exchange exchange() {
		return ExchangeBuilder.fanoutExchange("RatingsEvents").build();
	}

	@Bean
	public Queue queue() {
		return QueueBuilder.durable("RatingsEvents").build();
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
	}

	@Autowired
	public void configure(AmqpAdmin admin) {
		admin.declareExchange(exchange());
		admin.declareQueue(queue());
		admin.declareBinding(binding());
	}
}
