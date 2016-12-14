package com.vvgomes.ratings.events;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRatedEvent {
  String id;
  String raterUsername;
  String receiverUsername;
  Integer stars;
}
