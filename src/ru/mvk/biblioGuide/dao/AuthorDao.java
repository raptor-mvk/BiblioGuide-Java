/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */
package ru.mvk.biblioGuide.dao;

import org.jetbrains.annotations.NotNull;
import ru.mvk.biblioGuide.model.Author;
import ru.mvk.iluvatar.dao.DaoImpl;
import ru.mvk.iluvatar.module.db.HibernateAdapter;

public class AuthorDao extends DaoImpl<Author, Integer> {
	public AuthorDao(@NotNull HibernateAdapter hibernateAdapter) {
		super(Author.class, Integer.class, hibernateAdapter);
	}
}
