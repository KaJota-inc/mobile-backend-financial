package com.example.springwithPostgreSql.repository;

import com.example.springwithPostgreSql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity searchUserEntityById(Long id);
}
