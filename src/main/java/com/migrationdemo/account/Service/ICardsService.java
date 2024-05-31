package com.migrationdemo.account.Service;

import com.migrationdemo.account.DTOs.CardsEntityDto;
import com.migrationdemo.account.Enums.CardType;

import java.util.List;

public interface ICardsService {

    CardsEntityDto createCard(CardType cardType);

    List<CardsEntityDto> getAllCards();

    CardsEntityDto getCard(Long cardId);

    void deleteCard(Long cardId);
}
