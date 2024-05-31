package com.migrationdemo.account.Service.Implimentation;

import com.migrationdemo.account.DTOs.AccountInput;
import com.migrationdemo.account.DTOs.CardInput;
import com.migrationdemo.account.Entity.AccountEntity;
import com.migrationdemo.account.DTOs.AccountEntityDto;
import com.migrationdemo.account.Entity.CardsEntity;
import com.migrationdemo.account.Mapper.AccountEntityMapper;
import com.migrationdemo.account.Producer.AccountProducer;
import com.migrationdemo.account.Repository.AccountRepository;
import com.migrationdemo.account.Service.IAccountService;
import com.migrationdemo.feignclient.UserClient;
import com.migrationdemo.feignclient.UserEntityDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountEntityMapper accountEntityMapper;

    @Autowired
    private UserClient userClient;

    @Autowired
    private AccountProducer accountProducer;


    @Override
    public AccountEntity getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public AccountEntity createAccount(AccountInput accountInput) {
        log.info("Creating account for user with id: " + accountInput.getUserId());

        UserEntityDto user = userClient.getUsers(accountInput.getUserId());
        if (user == null) {
            throw new RuntimeException("User with id " + accountInput.getUserId() + " does not exist");
        }

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserId(accountInput.getUserId());
        accountEntity.setAccountNumber(accountInput.getAccountNumber());
        accountEntity.setBalance(accountInput.getBalance());


        List<CardsEntity> cards = new ArrayList<>();
        for (CardInput cardInput : accountInput.getCards()) {
            CardsEntity newCard = new CardsEntity();
            newCard.setCardType(cardInput.getCardType());
            newCard.setCardNumber(generateCardNumber());
            newCard.setCvv(generateCvv());
            newCard.setExpiryDate(generateExpiryDate());
            newCard.setAccountEntity(accountEntity);
            cards.add(newCard);
        }
        accountEntity.setCards(cards);

        return accountRepository.save(accountEntity);
    }

    private String generateCardNumber() {
        return String.format("%016d", new Random().nextInt(Integer.MAX_VALUE));
    }

    private int generateCvv() {
        return new Random().nextInt(900) + 100;
    }

    private String generateExpiryDate() {
        return LocalDate.now().plusYears(10).format(DateTimeFormatter.ofPattern("MM/yy"));
    }


    @Override
    public AccountEntity updateAccount(AccountEntity accountEntity) {
        return accountRepository.save(accountEntity);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountEntityDto> getAllAccounts() {
        List<AccountEntity> userEntity = accountRepository.findAll();
        return userEntity.stream().map(accountEntityMapper::toDto).collect(Collectors.toList());
    }

}
