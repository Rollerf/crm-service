package com.agilemonkeys.crmservice.repository;

import com.agilemonkeys.crmservice.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        User user =
                User.builder()
                        .userName("testUser")
                        .password("testUser")
                        .build();

        entityManager.persist(user);
    }

    @Test
    void whenFindByUserName_thenReturnUser(){
        User user = userRepository.findByUserName("testUser");
        assertEquals("testUser", user.getUserName());
    }

    @Test
    void isExistsByUserName_thenReturnTrue(){
        boolean exists = userRepository.existsByUserName("testUser");
        assertTrue(exists);
    }
}