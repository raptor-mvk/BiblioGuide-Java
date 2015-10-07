/**
 * (c) raptor_MVK, 2014. All rights reserved.
 */
package ru.mvk.biblioGuide.model;

import org.hibernate.annotations.Formula;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mvk.iluvatar.utils.IluvatarUtils;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "book")
public final class Book implements Serializable {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "rowid", nullable = false, unique = true)
  private int id;

  @NotNull
  @Column(name = "name", nullable = false, length = 40)
  private String name;

  @NotNull
  @Column(name = "lowerName", nullable = false, length = 40)
  private String lowerName;

  @Column(name = "author", nullable = false)
  private int author;

  @Column(name = "bookCase", nullable = false)
  private int bookCase;

  @Column(name = "shelf", nullable = false)
  private byte shelf;

  @NotNull
  @Formula("(select author.surname from author where author.rowid=author)")
  private String authorName;

  @NotNull
  @Formula("(select bookcase.name from bookcase where bookcase.rowid=bookcase)")
  private String bookCaseName;

  public Book() {
    name = "";
    lowerName = "";
    authorName = "";
    bookCaseName = "";
    author = 1;
    bookCase = 1;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @NotNull
  public String getName() {
    return name;
  }

  public int getAuthor() {
    return author;
  }

  public void setAuthor(int author) {
    this.author = author;
  }

  public int getBookCase() {
    return bookCase;
  }

  public void setBookCase(int bookCase) {
    this.bookCase = bookCase;
  }

  public byte getShelf() {
    return shelf;
  }

  public void setShelf(byte shelf) {
    this.shelf = shelf;
  }

  @NotNull
  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(@NotNull String authorName) {
    this.authorName = authorName;
  }

  @NotNull
  public String getBookCaseName() {
    return bookCaseName;
  }

  public void setBookCaseName(@NotNull String bookCaseName) {
    this.bookCaseName = bookCaseName;
  }

  public void setName(@NotNull String name) {
    this.name = name;
    this.lowerName = IluvatarUtils.normalizeString(name);
  }

  @NotNull
  public String getLowerName() {
    return lowerName + name;
  }

  public boolean equals(@NotNull Book book) {
    return (id == book.id) && (author == book.author) && (shelf == book.shelf) &&
        (bookCase == book.bookCase) && name.equals(book.name);
  }

  @Override
  public boolean equals(@Nullable Object o) {
    return this == o || o != null && getClass() == o.getClass() &&
                            this.equals((Book) o);
  }

  @Override
  public int hashCode() {
    int result = 31 * id + name.hashCode();
    result = 31 * result + author;
    result = 31 * result + (int) shelf;
    return 31 * result + bookCase;
  }

  @Override
  public String toString() {
    return name;
  }
}
