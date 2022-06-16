package com.alnicode.funvirtualreading.persistence.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


import static com.alnicode.funvirtualreading.util.AppConstants.DATE_FORMAT;
import static com.alnicode.funvirtualreading.util.AppConstants.DATE_TIME_FORMAT;

/**
 * The person entity model.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Size(min = 3, max = 40)
    @NotNull
    private String name;

    @Size(min = 3, max = 60)
    @NotNull
    private String lastname;

    @Size(min = 12, max = 200)
    @NotNull
    @Column(unique = true)
    private String email;

    @DateTimeFormat(iso = ISO.DATE, pattern = DATE_FORMAT)
    @NotNull
    private LocalDate birthday;

    @NotNull
    @Min(1L)
    @Max(Long.MAX_VALUE)
    @Column(name = "nationality_id")
    private Long nationalityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality_id", insertable = false, updatable = false)
    private Nationality nationality;

    @DateTimeFormat(iso = ISO.DATE_TIME, pattern = DATE_TIME_FORMAT)
    @Column(name = "registration_date")
    private LocalDateTime date;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Book> publishedBooks = Collections.emptySet();

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Comment> publishedComments = Collections.emptySet();

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Collection> collections = Collections.emptySet();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "persons_books",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> likes = new HashSet<>();

    /**
     * Set the registration date before being registered.
     */
    @PrePersist
    public void setRegistrationDate() {
        if (this.date == null) {
            this.date = LocalDateTime.now();
        }
    }

    /**
     * Add a book to the likes collection.
     *
     * @param book the book to be added.
     */
    public void addLike(Book book) {
        this.likes.add(book);
        book.getPersons().add(this);
    }

    /**
     * Remove a book to the likes collection.
     *
     * @param book the book to be removed.
     */
    public void removeLike(Book book) {
        this.likes.remove(book);
        book.getPersons().remove(this);
    }
}
