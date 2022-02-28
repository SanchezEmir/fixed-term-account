package com.nttdata.fixedtermaccount.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.fixedtermaccount.entity.FixedTerm;
import com.nttdata.fixedtermaccount.entity.dto.Customer;
import com.nttdata.fixedtermaccount.repository.IFixedTermRepository;
import com.nttdata.fixedtermaccount.service.IFixedTermService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FixedTermServiceImpl implements IFixedTermService {
  
  private final WebClient webClient;
  //private final WebClient webClientNumber;
  private final ReactiveCircuitBreaker reactiveCircuitBreaker;

  @Value("${config.base.apigateway}")
  private String url;

  /*
   * @Value("${config.base.apigateway}") private String urln;
   */

  public FixedTermServiceImpl(ReactiveResilience4JCircuitBreakerFactory circuitBreakerFactory) {
      this.webClient = WebClient.builder().baseUrl(this.url).build();
      //this.webClientNumber = WebClient.builder().baseUrl(this.url).build();
      this.reactiveCircuitBreaker = circuitBreakerFactory.create("customer");
  }
  @Autowired
  IFixedTermRepository repo;

  @Override
  public Mono<FixedTerm> findById(String id) {
    return repo.findById(id);
  }

  @Override
  public Flux<FixedTerm> findAll() {
    return repo.findAll();
  }

  @Override
  public Mono<FixedTerm> create(FixedTerm fixedTerm) {
    return repo.save(fixedTerm);
  }

  @Override
  public Mono<FixedTerm> update(FixedTerm fixedTerm) {
    return repo.save(fixedTerm);
  }

  @Override
  public Mono<Boolean> delete(String id) {
    return repo.findById(id)
        .flatMap(cf -> repo.delete(cf)
            .then(Mono.just(Boolean.TRUE)))
        .defaultIfEmpty(Boolean.FALSE);
  }

  @Override
  public Mono<Long> countCustomerAccountBank(String id) {
    return repo.findByCustomerId(id).count();
  }

  @Override
  public Mono<Long> countCustomerAccountBankDocumentNumber(String number) {
    return repo.findByCustomerDocumentNumber(number).count();
  }

  @Override
  public Mono<Customer> findCustomerByDocumentNumber(String number) {
    return reactiveCircuitBreaker.run(webClient.get()
        .uri(this.url + "/customer/documentNumber/{number}",number)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(Customer.class),
        throwable -> {
          return this.getDefaultCustomer();
        });
  }

  @Override
  public Mono<Customer> findCustomerById(String id) {
    return reactiveCircuitBreaker.run(webClient.get()
        .uri(this.url + "/customer/find/{id}",id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(Customer.class),
        throwable -> {
          return this.getDefaultCustomer();
        });
  }

  @Override
  public Mono<FixedTerm> findByCardNumber(String numberAccount) {
    return repo.findByCardNumber(numberAccount);
  }

  @Override
  public Flux<FixedTerm> findAccountCustomerById(String id) {
    return repo.findByCustomerId(id);
  }
  
  public Mono<Customer> getDefaultCustomer() {
    Mono<Customer> customer = Mono.just(new Customer());
    return customer;
  }

}
