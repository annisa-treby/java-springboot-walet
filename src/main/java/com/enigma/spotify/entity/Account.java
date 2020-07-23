package com.enigma.spotify.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mst_account")
@EqualsAndHashCode
@Getter @Setter
public class Account {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private boolean active;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("account")
    @JsonBackReference
    private List<Playlist> playlists;

    @OneToOne
   // @JsonIgnoreProperties("account")
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToOne(mappedBy = "owner")
    @JsonIgnoreProperties("owner")
    private Wallet wallet;

    public Account() {
    }

    public Account(boolean active, Profile profile, Wallet wallet) {
        this.active = active;
        this.profile = profile;
        this.wallet = wallet;
    }

}
