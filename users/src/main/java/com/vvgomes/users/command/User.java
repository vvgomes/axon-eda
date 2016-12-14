package com.vvgomes.users.command;

import com.vvgomes.users.events.UserSignedUpEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class User {
  @AggregateIdentifier
  private String username;

  private User() {}

  @CommandHandler
  public User(SignUpUserCommand command) {
    apply(new UserSignedUpEvent(command.getUsername(), command.getFullname()));
  }

  @EventSourcingHandler
  public void on(UserSignedUpEvent event) {
    username = event.getUsername();
  }
}
