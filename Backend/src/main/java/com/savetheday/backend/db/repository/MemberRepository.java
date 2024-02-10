package com.savetheday.backend.db.repository;

import com.savetheday.backend.db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
