package com.vvgomes.ratings.command;

import com.vvgomes.ratings.events.UserRatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Rating {
  @AggregateIdentifier
  private String id;

  private Rating() {
  }

  @CommandHandler
  public Rating(RateUserCommand command) {
    String rater = command.getRaterUsername();
    String receiver = command.getReceiverUsername();
    Assert.isTrue(!rater.equals(receiver), "You can't rate yourself.");
    apply(new UserRatedEvent(command.getId(), rater, receiver, command.getStars()));
  }

  @EventSourcingHandler
  public void on(UserRatedEvent event) {
    id = event.getId();
  }
}
