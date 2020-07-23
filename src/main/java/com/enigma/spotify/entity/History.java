package com.enigma.spotify.entity;

import com.enigma.spotify.enums.HistoryTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "mst_history")
@Getter
@Setter
@EqualsAndHashCode
public class History {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Enumerated(EnumType.STRING)
    private HistoryTypeEnum type;

    private Double amount;

    private Timestamp trxDate;

    @ManyToOne (fetch = FetchType.LAZY)
    @JsonIgnoreProperties("histories")
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    public History() {
    }

    public History(String id, HistoryTypeEnum type, Double amount, Timestamp trxDate, Wallet wallet) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.trxDate = trxDate;
        this.wallet = wallet;
    }

}
