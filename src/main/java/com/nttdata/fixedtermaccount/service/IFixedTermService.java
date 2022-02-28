package com.nttdata.fixedtermaccount.service;

import com.nttdata.fixedtermaccount.entity.FixedTerm;
import com.nttdata.fixedtermaccount.entity.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFixedTermService {
  
  public Mono<FixedTerm> findById(String id);
  
  public Flux<FixedTerm> findAll();
  
  public Mono<FixedTerm> create(FixedTerm fixedTerm);
  
  public Mono<FixedTerm> update(FixedTerm fixedTerm);
  
  public Mono<Boolean> delete(String id);
  
  Mono<Long> countCustomerAccountBank(String id);
  
  Mono<Long> countCustomerAccountBankDocumentNumber(String number);
  
  Mono<Customer> findCustomerByDocumentNumber(String number);
  
  Mono<Customer> findCustomerById(String id);
  
  public Mono<FixedTerm> findByCardNumber(String numberAccount);
  
  Flux<FixedTerm> findAccountCustomerById(String id);

}
