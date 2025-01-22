package com.anylearn.anylearn_api.domain.user.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.anylearn.anylearn_api.domain.user.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>, CrudRepository<User, Long> {
    Optional<User> findByPhone(String phone);

    Page<User> findByRole(String role, Pageable pageable);

}