package com.enigma.spotify.entity;

import com.enigma.spotify.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.action.internal.OrphanRemovalAction;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mst_profile")
@Getter @Setter
@EqualsAndHashCode
public class Profile {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private String firstName;

    private String middleName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private String email;

    private String phone;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;

    private String location;

    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JsonBackReference
    @OneToOne(mappedBy = "profile")
    private Account account;

    public Profile() {
    }

    public Profile(String firstName, String middleName, String lastName, GenderEnum gender, String email, String phone, Date birthDate, String location, Account account) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.location = location;
        this.account = account;
    }

}
