/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.biblioGuide.dao;

import org.jetbrains.annotations.NotNull;
import ru.mvk.biblioGuide.model.Book;
import ru.mvk.iluvatar.dao.DaoImpl;
import ru.mvk.iluvatar.module.db.HibernateAdapter;

public class BookDao extends DaoImpl<Book, Integer> {
	public BookDao(@NotNull HibernateAdapter hibernateAdapter) {
		super(Book.class, Integer.class, hibernateAdapter);
	}
}
