package com.migrationdemo.account.Service.Implimentation;

import com.migrationdemo.account.DTOs.CardsEntityDto;
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

import java.util.ArrayList;
import java.util.List;
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
    public AccountEntity createAccount(AccountEntity accountEntity) {
        log.info("creating account for user with id: " + accountEntity.getUserId());
        UserEntityDto user = userClient.getUsers(accountEntity.getUserId());
        if (user == null) {
            throw new RuntimeException("User with id " + accountEntity.getUserId() + " does not exist");
        } else {
            AccountEntityDto accountEntityDto = accountEntityMapper.toDto(accountEntity);

            com.migrationdemo.feignclient.AccountEntityDto accountEntityDto1 = new com.migrationdemo.feignclient.AccountEntityDto();
            accountEntityDto1.setUserId(accountEntityDto.getUserId());
            accountEntityDto1.setAccountNumber(accountEntityDto.getAccountNumber());
            accountEntityDto1.setBalance(accountEntityDto.getBalance());
            accountProducer.sendMessage(accountEntityDto1);

            List<CardsEntity> cards = new ArrayList<>();
            for (CardsEntityDto card : accountEntityDto.getCards()) {
                CardsEntity newCard = new CardsEntity();
                newCard.setCardNumber(card.getCardNumber());
                newCard.setCardType(card.getCardType());
                newCard.setCvv(card.getCvv());
                newCard.setExpiryDate(card.getExpiryDate());
                newCard.setAccountEntity(accountEntity);
                cards.add(newCard);
            }

            accountEntity.setCards(cards);
            return accountRepository.save(accountEntity);
        }
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
