package com.microservice.email.dto;

public record EmailRecordDTO(Long userId, String emailTo, String subject, String text) {
}
