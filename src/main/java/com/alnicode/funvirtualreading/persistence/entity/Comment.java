package com.alnicode.funvirtualreading.persistence.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


import static com.alnicode.funvirtualreading.constants.DateFormatConstants.DATE_TIME_FORMAT;

/**
 * The comment entity model.
 *
 * @author Alben Bustamante
 * @version 1.0
 * @since 1.0
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 400)
    private String body;

    @DateTimeFormat(iso = ISO.DATE_TIME, pattern = DATE_TIME_FORMAT)
    @Column(name = "publication_date")
    private LocalDateTime date;

    @Min(1L)
    @Max(Long.MAX_VALUE)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Min(1L)
    @Max(Long.MAX_VALUE)
    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;

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
        Comment comment = (Comment) o;
        return id != null && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
