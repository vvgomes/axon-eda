package com.vvgomes.ratings.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RateUserCommand {
  @TargetAggregateIdentifier
  String id;
  String raterUsername;
  String receiverUsername;
  Integer stars;
}
