package com.vvgomes.ratings.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/ratings/commands")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandApi {
  CommandGateway commandGateway;

  @PostMapping("/add")
  public void add(@RequestBody RatingRequestBody body) {
    String id = UUID.randomUUID().toString();
    String rater = body.getRaterUsername();
    String receiver = body.getReceiverUsername();
    Integer stars = body.getStars();
    commandGateway.send(new RateUserCommand(id, rater, receiver, stars));
  }
}
