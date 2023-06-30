package com.lithan.mow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lithan.mow.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
