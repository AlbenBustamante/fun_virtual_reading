package com.alnicode.funvirtualreading.persistence.entity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


import static com.alnicode.funvirtualreading.constants.DateFormatConstants.DATE_TIME_FORMAT;

/**
 * The book entity model.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(unique = true, nullable = false, length = 130)
    private String title;

    @Column(unique = true, nullable = false, length = 600)
    private String synopsis;

    @Column(unique = true, nullable = false, length = 4000)
    private String body;

    @Min(1L)
    @Max(Long.MAX_VALUE)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Min(1L)
    @Max(Long.MAX_VALUE)
    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private Genre genre;

    @DateTimeFormat(iso = ISO.DATE_TIME, pattern = DATE_TIME_FORMAT)
    @Column(name = "publication_date", nullable = false)
    private LocalDateTime date;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Comment> comments = Collections.emptySet();

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<CollectionsBook> collections = Collections.emptySet();

    @ManyToMany(
            mappedBy = "likes", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> users = Collections.emptySet();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "books_tags",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private Set<Tag> tags = new HashSet<>();

    /**
     * Set the publication date before being registered.
     */
    @PrePersist
    public void setPublicationDate() {
        if (this.date == null) {
            this.date = LocalDateTime.now();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return bookId != null && Objects.equals(bookId, book.bookId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    /**
     * Add an existing tag to the tags list.
     *
     * @param tag the tag to add
     */
    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getBooks().add(this);
    }

    /**
     * Remove an existing tag to the tags list.
     *
     * @param tag the tag to remove
     */
    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getBooks().remove(this);
    }

}
