package com.objective.bankObjective.domain.repository;

import com.objective.bankObjective.domain.models.Conta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends MongoRepository<Conta, String> {

    Optional<Conta> findByNumero(Long numero);
}
