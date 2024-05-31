package com.migrationdemo.account.Entity;

import com.migrationdemo.account.Enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class CardsEntity {

    @Id
    @SequenceGenerator(
            name = "Card_sequence",
            sequenceName = "Card_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Card_sequence")
    private Long CardID;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "cardType")
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(name = "expiryDate")
    private String expiryDate;

    @Column(name = "cvv")
    private int cvv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;

}