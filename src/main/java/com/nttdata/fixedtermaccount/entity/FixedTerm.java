package com.nttdata.fixedtermaccount.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.fixedtermaccount.entity.dto.Customer;
import com.nttdata.fixedtermaccount.entity.dto.Managers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection =  "fixed_term")
@AllArgsConstructor
@NoArgsConstructor
public class FixedTerm {
  
  @Id
  private String id;
  
  @NotNull
  private Customer customer;
  
  @NotNull
  private String cardNumber;
  
  @NotNull
  private Integer limitTransactions;
  
  @NotNull
  private Integer freeTransactions;
  
  @NotNull
  private Double commissionTransactions;
  
  @NotNull
  private Double balance;
  
  private Integer limitDeposits;
  
  private Integer limitDraft;
  
  @NotNull
  private LocalDate allowDateTransaction;
  
  private LocalDateTime createdAt;
  
  private List<Managers> owners;
  
  private List<Managers> signatories;

}
