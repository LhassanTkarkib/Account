package com.migrationdemo.account.Repository;

import com.migrationdemo.account.Entity.AccountEntity;
import com.migrationdemo.account.Entity.CardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository<CardsEntity, Long> {
}
