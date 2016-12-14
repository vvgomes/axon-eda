package com.vvgomes.users.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vvgomes.users.query.UserView;

@Repository
public interface UserViewRepository extends JpaRepository<UserView, String> {}

