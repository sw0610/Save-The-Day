package com.savetheday.backend.db.repository;

import com.savetheday.backend.db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}
