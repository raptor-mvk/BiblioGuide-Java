/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */
package ru.mvk.biblioGuide.dao;

import org.jetbrains.annotations.NotNull;
import ru.mvk.biblioGuide.model.BookCase;
import ru.mvk.iluvatar.dao.DaoImpl;
import ru.mvk.iluvatar.module.db.HibernateAdapter;

public class BookCaseDao extends DaoImpl<BookCase, Integer> {
	public BookCaseDao(@NotNull HibernateAdapter hibernateAdapter) {
		super(BookCase.class, Integer.class, hibernateAdapter);
	}
}
