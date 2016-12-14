package com.vvgomes.ratings.query;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingView {
  @Id
  String id;
  @ManyToOne
  UserView rater;
  @ManyToOne
  UserView receiver;
  Integer stars;

  private RatingView() {
    this(null, null, null, null);
  }
}
