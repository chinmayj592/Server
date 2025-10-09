package com.example.server.repository;

import com.example.server.entity.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BuilderRepository extends JpaRepository<Builder, UUID> {
}