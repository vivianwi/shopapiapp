package edu.school21.shopapi.model;

import edu.school21.shopapi.model.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "client", schema = "shopapp")
public class Client extends BaseEntity {

    @Column(name = "client_name", nullable = false, length = 100)
    private String clientName;

    @Column(name = "client_surname", nullable = false, length = 100)
    private String clientSurname;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(columnDefinition = "CHAR(1)")
    private String gender;

    @Column(name = "registration_date")
    private Instant registrationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "address_id")
    private Address address;
}