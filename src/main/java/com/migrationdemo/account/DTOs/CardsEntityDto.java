package com.migrationdemo.account.DTOs;

import com.migrationdemo.account.Enums.CardType;
import lombok.Value;

import java.io.Serializable;


@Value
public class CardsEntityDto implements Serializable {
    Long cardNumber;
    CardType cardType;
    String expiryDate;
    int cvv;
}