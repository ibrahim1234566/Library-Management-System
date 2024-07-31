package com.example.ibrahim.library_sys.repository;

import com.example.ibrahim.library_sys.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {
}
