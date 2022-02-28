package com.nttdata.fixedtermaccount.entity.dto;


import com.nttdata.fixedtermaccount.entity.enums.ESubType;

import lombok.Data;

@Data
public class SubType {

  private String id;

  private ESubType value;

}
