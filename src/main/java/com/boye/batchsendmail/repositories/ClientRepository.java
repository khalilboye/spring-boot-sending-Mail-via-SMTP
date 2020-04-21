package com.boye.batchsendmail.repositories;

import com.boye.batchsendmail.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
