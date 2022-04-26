package com.alnicode.funvirtualreading.persistence.entity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "collections")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    @EqualsAndHashCode.Include
    private Long collectionId;

    @NotNull
    @Size(min = 6, max = 200)
    private String name;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @Column(name = "creation_date")
    private LocalDateTime date;

    @NotNull
    @Min(1L)
    @Max(Long.MAX_VALUE)
    @Column(name = "person_id")
    private Long personId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;

    @OneToMany(mappedBy = "collection", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    private Set<CollectionsBook> books = Collections.emptySet();

    @PrePersist
    private void setCreationDate() {
        if (this.date == null) {
            this.date = LocalDateTime.now();
        }
    }
}
