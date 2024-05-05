package com.microservice.email.repository;

import com.microservice.email.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmailRepository extends JpaRepository<Email, Long> {
}
