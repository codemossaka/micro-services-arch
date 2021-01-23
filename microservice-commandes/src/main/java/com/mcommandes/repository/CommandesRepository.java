package com.mcommandes.repository;

import com.mcommandes.model.Commandes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandesRepository extends JpaRepository<Commandes, Integer> {
}
