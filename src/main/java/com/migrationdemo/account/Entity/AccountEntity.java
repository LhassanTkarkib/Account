package com.migrationdemo.account.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class AccountEntity {

    @Id
    @SequenceGenerator(
            name = "Account_sequence",
            sequenceName = "Account_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Account_sequence")
    private Long id;

    @Column(name = "accountNumber")
    private String accountNumber;

    @Column(name = "balance")
    private double balance;

    @Column(name = "UserId")
    private Long UserId;


}
