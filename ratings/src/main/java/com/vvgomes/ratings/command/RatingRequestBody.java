package com.vvgomes.ratings.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingRequestBody {
  String raterUsername;
  String receiverUsername;
  Integer stars;
}
