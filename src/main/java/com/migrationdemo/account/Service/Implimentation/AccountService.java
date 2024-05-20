package com.migrationdemo.account.Service.Implimentation;

import com.migrationdemo.account.DTOs.AccountEntityDto;
import com.migrationdemo.account.Entity.AccountEntity;
import com.migrationdemo.account.Mapper.AccountEntityMapper;
import com.migrationdemo.account.Repository.AccountRepository;
import com.migrationdemo.account.Service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountEntityMapper accountEntityMapper;

    @Override
    public AccountEntity getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public AccountEntity createAccount(AccountEntity accountEntity) {
        return accountRepository.save(accountEntity);
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
