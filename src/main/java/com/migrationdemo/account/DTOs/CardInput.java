package com.migrationdemo.account.DTOs;

import com.migrationdemo.account.Enums.CardType;
import lombok.Data;

@Data
public class CardInput {
    private CardType cardType;

}
