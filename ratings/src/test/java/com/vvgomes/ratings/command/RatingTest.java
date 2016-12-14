package com.vvgomes.ratings.command;

import com.vvgomes.ratings.events.UserRatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

public class RatingTest {
  private FixtureConfiguration<Rating> fixture;

  @Before
  public void setUp() throws Exception {
    fixture = new AggregateTestFixture(Rating.class);
  }

  @Test
  public void aUserRatesAnother() {
    fixture
      .givenNoPriorActivity()
      .when(new RateUserCommand("666", "vgomes", "angoh", 5))
      .expectEvents(new UserRatedEvent("666", "vgomes", "angoh", 5));
  }

  @Test
  public void aUserRatesHimself() {
    fixture
      .givenNoPriorActivity()
      .when(new RateUserCommand("667", "vgomes", "vgomes", 5))
      .expectNoEvents()
      .expectException(IllegalArgumentException.class);
  }
}