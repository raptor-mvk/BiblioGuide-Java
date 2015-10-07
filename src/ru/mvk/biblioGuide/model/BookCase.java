/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */
package ru.mvk.biblioGuide.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mvk.iluvatar.descriptor.field.RefAble;
import ru.mvk.iluvatar.utils.IluvatarUtils;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "bookCase")
public final class BookCase implements Serializable, RefAble<Integer> {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "rowid", nullable = false, unique = true)
	private int id;

	@NotNull
	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@NotNull
	@Column(name = "lowerName", nullable = false, length = 20)
	private String lowerName;

	@Column(name = "shelvesCount", nullable = false)
	private byte shelvesCount;

	public BookCase() {
		name = "";
		lowerName = "";
	}

	@NotNull
	public Integer getId() {
		return id;
	}

	public void setId(@NotNull Integer id) {
		this.id = id;
	}

	@NotNull
	public String getName() {
		return name;
	}

	public byte getShelvesCount() {
		return shelvesCount;
	}

	public void setShelvesCount(byte shelvesCount) {
		this.shelvesCount = shelvesCount;
	}

	public void setName(@NotNull String name) {
		this.name = name;
		this.lowerName = IluvatarUtils.normalizeString(name);
	}

	@NotNull
	public String getLowerName() {
		return lowerName + name;
	}

	public boolean equals(@NotNull BookCase bookCase) {
		return (id == bookCase.id) && (shelvesCount == bookCase.shelvesCount) &&
				name.equals(bookCase.name);
	}

	@Override
	public boolean equals(@Nullable Object o) {
		return this == o || o != null && getClass() == o.getClass() &&
				this.equals((BookCase) o);
	}

	@Override
	public int hashCode() {
		int result = 31 * id + name.hashCode();
		return 31 * result + shelvesCount;
	}

	@Override
	public String toString() {
		return name;
	}
}
