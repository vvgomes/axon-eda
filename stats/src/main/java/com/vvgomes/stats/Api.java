package com.vvgomes.stats;

import com.vvgomes.ratings.events.UserRatedEvent;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@ProcessingGroup("amqpEvents")
@RestController
@RequestMapping("/stats")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Api {
  Map<String, Double> topRatedUsers = new TreeMap<>();

  @EventHandler
  public void on(UserRatedEvent event) {
    Double lastRating = event.getStars().doubleValue();
    topRatedUsers.compute(event.getReceiverUsername(),
      (user, averageRating) ->
        Optional
          .ofNullable(averageRating)
          .map(rating -> (rating + lastRating) / 2)
          .orElse(lastRating));
  }

  @GetMapping
  public Map<String, Double> getStats() {
    return topRatedUsers;
  }
}
