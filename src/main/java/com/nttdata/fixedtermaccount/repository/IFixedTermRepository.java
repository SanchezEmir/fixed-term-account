package com.nttdata.fixedtermaccount.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.fixedtermaccount.entity.FixedTerm;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFixedTermRepository extends ReactiveMongoRepository<FixedTerm, String> {
  
  Flux<FixedTerm> findByCustomerId(String id);
  
  Flux<FixedTerm> findByCustomerDocumentNumber(String number);
  
  Mono<FixedTerm> findByCardNumber(String id);

}
