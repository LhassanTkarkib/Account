package com.migrationdemo.account.Service.Implimentation;

import com.migrationdemo.account.DTOs.CardsEntityDto;
import com.migrationdemo.account.Entity.CardsEntity;
import com.migrationdemo.account.Enums.CardType;
import com.migrationdemo.account.Mapper.CardsEntityMapper;
import com.migrationdemo.account.Repository.CardsRepository;
import com.migrationdemo.account.Service.ICardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CardsService implements ICardsService {

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private CardsEntityMapper cardsEntityMapper;

    @Override
    public CardsEntityDto createCard(CardType cardType) {
        Random random = new Random();
        String cardNumber = String.format("%016d", random.nextInt(Integer.MAX_VALUE));
        String cvv = String.format("%03d", random.nextInt(999));
        String expiryDate = LocalDate.now().plusYears(10).format(DateTimeFormatter.ofPattern("MM/yy"));

        CardsEntity newCard = new CardsEntity();
        newCard.setCardNumber(cardNumber);
        newCard.setCardType(cardType);
        newCard.setCvv(Integer.parseInt(cvv));
        newCard.setExpiryDate(expiryDate);

        CardsEntity savedCard = cardsRepository.save(newCard);
        return cardsEntityMapper.toDto(savedCard);
    }

    @Override
    public List<CardsEntityDto> getAllCards() {
        return cardsRepository.findAll().stream().map(cardsEntityMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CardsEntityDto getCard(Long cardId) {
        return cardsEntityMapper.toDto(cardsRepository.findById(cardId).get());
    }


    @Override
    public void deleteCard(Long cardId) {
        cardsRepository.deleteById(cardId);
    }

}