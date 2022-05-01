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
import javax.persistence.ManyToMany;
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

import static com.alnicode.funvirtualreading.util.AppConstants.DATE_TIME_FORMAT;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    @EqualsAndHashCode.Include
    private Long bookId;

    @NotNull
    @Size(min = 10, max = 130)
    private String title;

    @NotNull
    @Size(min = 100, max = 600)
    private String sypnosis;

    @NotNull
    @Size(min = 1000, max = 4000)
    private String body;

    @NotNull
    @Min(1L)
    @Max(Long.MAX_VALUE)
    @Column(name = "person_id")
    private Long personId;

    @NotNull
    @Min(1L)
    @Max(Long.MAX_VALUE)
    @Column(name = "genre_id")
    private Long genreId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private Genre genre;

    @DateTimeFormat(iso = ISO.DATE_TIME, pattern = DATE_TIME_FORMAT)
    @Column(name = "publication_date")
    private LocalDateTime date;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    private Set<Comment> comments = Collections.emptySet();

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    private Set<CollectionsBook> collections = Collections.emptySet();

    @ManyToMany(
        mappedBy = "likes", fetch = FetchType.LAZY, 
        cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Person> persons = Collections.emptySet();

    @PrePersist
    public void setPublicationDate() {
        if (this.date == null) {
            this.date = LocalDateTime.now();
        }
    }
}
