package com.matt.feng.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "user_detail")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @JsonProperty(index = 1)
    private String title;

    @JsonProperty(value = "firstn", index = 2)
    @Column(name = "first_name")
    private String firstName;

    @JsonProperty(value = "lastname", index = 3)
    @Column(name = "last_name")
    private String lastName;

    private String gender;

    @OneToOne(cascade = {ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
}
