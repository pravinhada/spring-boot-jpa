package com.example.persistence.jpahibernate.repo;

import com.example.persistence.jpahibernate.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
