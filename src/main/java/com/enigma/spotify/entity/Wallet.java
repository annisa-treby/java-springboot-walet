package com.enigma.spotify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mst_wallet")
@Getter @Setter
@EqualsAndHashCode
public class Wallet {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private Double balance;

    @OneToMany(mappedBy = "wallet")
    @JsonIgnoreProperties("wallet")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "wallet")
    @JsonIgnore
    private List<History> histories;

    @OneToOne
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties("wallet")
    private Account owner;


    public Wallet() {
    }

    public Wallet(String id, Double balance) {
        this.id = id;
        this.balance = balance;
    }

}