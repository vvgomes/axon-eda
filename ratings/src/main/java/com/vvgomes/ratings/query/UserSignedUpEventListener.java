package com.vvgomes.ratings.query;

import com.vvgomes.users.events.UserSignedUpEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("amqpEvents")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSignedUpEventListener {
  UserViewRepository users;

  @EventHandler
  public void on(UserSignedUpEvent event) {
    users.save(new UserView(event.getUsername(), event.getFullname()));
  }
}
