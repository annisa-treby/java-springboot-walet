package com.enigma.spotify.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "mst_transaction")
@Getter @Setter
@EqualsAndHashCode
public class Transaction {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp trxDate;

    private Double amount;

    private Double albumDiscount;

    @ManyToOne
    @JsonIgnoreProperties("transactionList")
    @JoinColumn(name = "song_id")
    private Song song;

    @Transient
    @JsonBackReference
    private Album album;

    @Transient
    @JsonBackReference(value = "song_reference")
    private String songs;

    @ManyToOne
    @JsonIgnoreProperties("transactions")
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    public Transaction() {
    }

    public Transaction(Timestamp trxDate, Double amount, Double albumDiscount, Wallet wallet) {
        this.trxDate = trxDate;
        this.amount = amount;
        this.albumDiscount = albumDiscount;
        this.wallet = wallet;
    }

}
