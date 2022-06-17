package com.alnicode.funvirtualreading.persistence.entity;

import java.time.LocalDate;
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


import static com.alnicode.funvirtualreading.constants.DateFormatConstants.DATE_FORMAT;
import static com.alnicode.funvirtualreading.constants.DateFormatConstants.DATE_TIME_FORMAT;

/**
 * The user entity model.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name", nullable = false, length = 40)
    private String firstname;

    @Column(name = "last_name", nullable = false, length = 60)
    private String lastname;

    @Column(unique = true, nullable = false, length = 200)
    private String email;

    @Column(nullable = false, unique = true, length = 60)
    private String username;

    @Column(nullable = false, length = 40)
    private String password;

    @DateTimeFormat(iso = ISO.DATE, pattern = DATE_FORMAT)
    @Column(nullable = false)
    private LocalDate birthday;

    @Min(1L)
    @Max(Long.MAX_VALUE)
    @Column(name = "nationality_id", nullable = false)
    private Long nationalityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality_id", insertable = false, updatable = false)
    private Nationality nationality;

    @DateTimeFormat(iso = ISO.DATE_TIME, pattern = DATE_TIME_FORMAT)
    @Column(name = "registration_date", nullable = false)
    private LocalDateTime date;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Book> publishedBooks = Collections.emptySet();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Comment> publishedComments = Collections.emptySet();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Collection> collections = Collections.emptySet();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "users_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> likes = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

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
        book.getUsers().add(this);
    }

    /**
     * Remove a book to the likes collection.
     *
     * @param book the book to be removed.
     */
    public void removeLike(Book book) {
        this.likes.remove(book);
        book.getUsers().remove(this);
    }

    /**
     * Add a role to the user.
     *
     * @param role the role to be added.
     */
    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    /**
     * Remove a role to the user.
     *
     * @param role the role to be removed.
     */
    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
