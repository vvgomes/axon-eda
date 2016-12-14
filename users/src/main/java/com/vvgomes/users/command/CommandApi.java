package com.vvgomes.users.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users/commands")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandApi {
  CommandGateway commandGateway;

  @PostMapping("/sign-up")
  public void signUp(@RequestBody Map<String, String> body) {
    String username = body.get("username");
    String fullname = body.get("fullname");
    commandGateway.send(new SignUpUserCommand(username, fullname));
  }
}
