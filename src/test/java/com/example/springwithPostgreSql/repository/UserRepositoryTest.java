package com.example.springwithPostgreSql.repository;

import com.example.springwithPostgreSql.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void itShouldSearchUserEntityById() {

        //given
        UserEntity user = new UserEntity();
        user.setName("Coded David");
        user.setPassword("David");
        user.setCity("Lagos");

        underTest.save(user);
        //when
        UserEntity exists = underTest.searchUserEntityById(user.getId());
        //then

        assertThat(exists).isEqualTo(user);

    }
}