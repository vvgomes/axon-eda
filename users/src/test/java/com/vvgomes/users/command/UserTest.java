package com.vvgomes.users.command;

import com.vvgomes.users.events.UserSignedUpEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
  private FixtureConfiguration<User> fixture;

  @Before
  public void setUp() throws Exception {
    fixture = new AggregateTestFixture(User.class);
  }

  @Test
  public void signUp() {
    fixture
      .givenNoPriorActivity()
      .when(new SignUpUserCommand("vgomes", "Vini Gomes"))
      .expectEvents(new UserSignedUpEvent("vgomes", "Vini Gomes"));
  }
}