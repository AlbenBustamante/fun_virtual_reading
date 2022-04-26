package com.alnicode.funvirtualreading.persistence.entity;

import java.util.Collections;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "nationalities")
public class Nationality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nationality_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Size(min = 3, max = 70)
    @NotNull
    @Column(unique = true)
    private String country;

    @Size(min = 5, max = 80)
    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany(
        mappedBy = "nationality", fetch = FetchType.LAZY,
        cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Person> persons = Collections.emptySet();
}
