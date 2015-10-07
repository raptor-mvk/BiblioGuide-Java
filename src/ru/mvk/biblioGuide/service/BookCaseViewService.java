/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */
package ru.mvk.biblioGuide.service;

import org.jetbrains.annotations.NotNull;
import ru.mvk.biblioGuide.dao.BookCaseDao;
import ru.mvk.biblioGuide.model.BookCase;
import ru.mvk.iluvatar.descriptor.ListViewInfo;
import ru.mvk.iluvatar.descriptor.ListViewInfoImpl;
import ru.mvk.iluvatar.descriptor.ViewInfo;
import ru.mvk.iluvatar.descriptor.ViewInfoImpl;
import ru.mvk.iluvatar.descriptor.column.NumColumnInfo;
import ru.mvk.iluvatar.descriptor.column.StringColumnInfo;
import ru.mvk.iluvatar.descriptor.field.NaturalFieldInfo;
import ru.mvk.iluvatar.descriptor.field.TextFieldInfo;
import ru.mvk.iluvatar.module.db.HibernateAdapter;
import ru.mvk.iluvatar.service.ViewServiceDescriptor;
import ru.mvk.iluvatar.service.ViewServiceImpl;
import ru.mvk.iluvatar.view.Layout;

public class BookCaseViewService extends ViewServiceImpl<BookCase> {
	public BookCaseViewService(@NotNull HibernateAdapter hibernateAdapter,
	                           @NotNull Layout layout) {
		super(new ViewServiceDescriptor<>(new BookCaseDao(hibernateAdapter),
				prepareBookCaseViewInfo(), prepareAuthorListViewInfo()), layout, "Шкафы");
	}

	@NotNull
	private static ViewInfo<BookCase> prepareBookCaseViewInfo() {
		@NotNull ViewInfo<BookCase> viewInfo = new ViewInfoImpl<>(BookCase.class);
		viewInfo.addFieldInfo("name", new TextFieldInfo("Название", 20));
		viewInfo.addFieldInfo("shelfCount", new NaturalFieldInfo<>(Byte.class,
				"Кол-во полок", 2));
		return viewInfo;
	}

	@NotNull
	private static ListViewInfo<BookCase> prepareAuthorListViewInfo() {
		@NotNull ListViewInfo<BookCase> listViewInfo = new ListViewInfoImpl<>(BookCase.class);
		listViewInfo.addColumnInfo("lowerName", new StringColumnInfo("Название", 20));
		listViewInfo.addColumnInfo("lowerInitials", new NumColumnInfo("Полок", 5));
		return listViewInfo;
	}
}
