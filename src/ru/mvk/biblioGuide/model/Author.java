/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */
package ru.mvk.biblioGuide.model;

import org.hibernate.annotations.Formula;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mvk.iluvatar.descriptor.field.RefAble;
import ru.mvk.iluvatar.utils.IluvatarUtils;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "author")
public final class Author implements Serializable, RefAble<Integer> {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "rowid", nullable = false, unique = true)
	private int id;

	@NotNull
	@Column(name = "surname", nullable = false, length = 30)
	private String surname;

	@NotNull
	@Column(name = "lowerSurname", nullable = false, length = 30)
	private String lowerSurname;

	@NotNull
	@Column(name = "initials", nullable = false, length = 10)
	private String initials;

	@NotNull
	@Column(name = "lowerInitials", nullable = false, length = 10)
	private String lowerInitials;

	public Author() {
		surname = "";
		lowerSurname = "";
		initials = "";
		lowerInitials = "";
	}

	@NotNull
	public Integer getId() {
		return id;
	}

	public void setId(@NotNull Integer id) {
		this.id = id;
	}

	@NotNull
	public String getSurname() {
		return surname;
	}

	public void setSurname(@NotNull String surname) {
		this.surname = surname;
		this.lowerSurname = IluvatarUtils.normalizeString(surname);
	}

	@NotNull
	public String getLowerSurname() {
		return lowerSurname + surname;
	}

	@NotNull
	public String getInitials() {
		return initials;
	}

	public void setInitials(@NotNull String initials) {
		this.initials = initials;
		this.lowerInitials = IluvatarUtils.normalizeString(initials);
	}

	@NotNull
	public String getLowerInitials() {
		return lowerInitials + initials;
	}

	public boolean equals(@NotNull Author author) {
		return (id == author.id) && surname.equals(author.surname) &&
				initials.equals(author.initials);
	}

	@Override
	public boolean equals(@Nullable Object o) {
		return this == o || o != null && getClass() == o.getClass() &&
				this.equals((Author) o);
	}

	@Override
	public int hashCode() {
		int result = 31 * id + surname.hashCode();
		return 31 * result + initials.hashCode();
	}

	@Override
	public String toString() {
		return initials + " " + surname;
	}
}
