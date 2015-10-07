/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.biblioGuide.service;

import org.jetbrains.annotations.NotNull;
import ru.mvk.biblioGuide.dao.AuthorDao;
import ru.mvk.biblioGuide.dao.BookCaseDao;
import ru.mvk.biblioGuide.dao.BookDao;
import ru.mvk.biblioGuide.model.Author;
import ru.mvk.biblioGuide.model.Book;
import ru.mvk.biblioGuide.model.BookCase;
import ru.mvk.iluvatar.dao.DaoListAdapter;
import ru.mvk.iluvatar.descriptor.ListViewInfo;
import ru.mvk.iluvatar.descriptor.ListViewInfoImpl;
import ru.mvk.iluvatar.descriptor.ViewInfo;
import ru.mvk.iluvatar.descriptor.ViewInfoImpl;
import ru.mvk.iluvatar.descriptor.column.DurationColumnInfo;
import ru.mvk.iluvatar.descriptor.column.FileSizeColumnInfo;
import ru.mvk.iluvatar.descriptor.column.NumColumnInfo;
import ru.mvk.iluvatar.descriptor.column.StringColumnInfo;
import ru.mvk.iluvatar.descriptor.field.ListAdapter;
import ru.mvk.iluvatar.descriptor.field.NaturalFieldInfo;
import ru.mvk.iluvatar.descriptor.field.RefFieldInfo;
import ru.mvk.iluvatar.descriptor.field.TextFieldInfo;
import ru.mvk.iluvatar.module.db.HibernateAdapter;
import ru.mvk.iluvatar.service.ViewServiceDescriptor;
import ru.mvk.iluvatar.service.ViewServiceImpl;
import ru.mvk.iluvatar.view.Layout;

public class BookViewService extends ViewServiceImpl<Book> {
	public BookViewService(@NotNull HibernateAdapter hibernateAdapter,
	                       @NotNull Layout layout) {
		super(new ViewServiceDescriptor<>(new BookDao(hibernateAdapter),
				prepareBookViewInfo(hibernateAdapter),
				prepareBookListViewInfo()), layout, "Книги");
	}

	@NotNull
	private static ViewInfo<Book> prepareBookViewInfo(@NotNull
	                                                    HibernateAdapter hibernateAdapter) {
		@NotNull ViewInfo<Book> viewInfo = new ViewInfoImpl<>(Book.class);
		viewInfo.addFieldInfo("name", new TextFieldInfo("Название", 40));
		@NotNull ListAdapter<Integer, Author> authorListAdapter =
				new DaoListAdapter<>(new AuthorDao(hibernateAdapter));
		viewInfo.addFieldInfo("author", new RefFieldInfo<>("Автор", 40, authorListAdapter));
		@NotNull ListAdapter<Integer, BookCase> bookCaseListAdapter =
				new DaoListAdapter<>(new BookCaseDao(hibernateAdapter));
		viewInfo.addFieldInfo("disc", new RefFieldInfo<>("Шкаф", 20, bookCaseListAdapter));
		viewInfo.addFieldInfo("shelf", new NaturalFieldInfo<>(Byte.class, "Полка", 2));
		return viewInfo;
	}

	@NotNull
	private static ListViewInfo<Book> prepareBookListViewInfo() {
		@NotNull ListViewInfo<Book> listViewInfo = new ListViewInfoImpl<>(Book.class);
		listViewInfo.addColumnInfo("lowerName", new StringColumnInfo("Название", 40));
		listViewInfo.addColumnInfo("authorName", new StringColumnInfo("Автор", 40));
		listViewInfo.addColumnInfo("bookCaseName", new StringColumnInfo("Шкаф", 20));
		listViewInfo.addColumnInfo("shelf", new NumColumnInfo("Полка", 5));
		return listViewInfo;
	}
}
