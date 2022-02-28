package com.nttdata.fixedtermaccount.entity.dto;


import com.nttdata.fixedtermaccount.entity.enums.ETypeCustomer;

import lombok.Data;

@Data
public class TypeCustomer {

  private String id;

  private ETypeCustomer value;

  private SubType subType;

}
