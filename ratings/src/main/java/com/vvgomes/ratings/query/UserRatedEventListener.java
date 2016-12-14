package com.vvgomes.ratings.query;

import com.vvgomes.ratings.events.UserRatedEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRatedEventListener {
  UserViewRepository users;
  RatingViewRepository ratings;

  @EventHandler
  public void on(UserRatedEvent event) {
    String id = event.getId();
    Integer stars = event.getStars();

    UserView rater = users.findOne(event.getRaterUsername());
    UserView receiver = users.findOne(event.getReceiverUsername());

    ratings.save(new RatingView(id, rater, receiver, stars));
  }
}
