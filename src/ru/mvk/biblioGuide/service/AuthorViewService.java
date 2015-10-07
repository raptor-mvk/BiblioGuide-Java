/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */
package ru.mvk.biblioGuide.service;

import org.jetbrains.annotations.NotNull;
import ru.mvk.biblioGuide.dao.AuthorDao;
import ru.mvk.biblioGuide.model.Author;
import ru.mvk.iluvatar.descriptor.ListViewInfo;
import ru.mvk.iluvatar.descriptor.ListViewInfoImpl;
import ru.mvk.iluvatar.descriptor.ViewInfo;
import ru.mvk.iluvatar.descriptor.ViewInfoImpl;
import ru.mvk.iluvatar.descriptor.column.StringColumnInfo;
import ru.mvk.iluvatar.descriptor.field.TextFieldInfo;
import ru.mvk.iluvatar.module.db.HibernateAdapter;
import ru.mvk.iluvatar.service.ViewServiceDescriptor;
import ru.mvk.iluvatar.service.ViewServiceImpl;
import ru.mvk.iluvatar.view.Layout;

public class AuthorViewService extends ViewServiceImpl<Author> {
	public AuthorViewService(@NotNull HibernateAdapter hibernateAdapter,
	                         @NotNull Layout layout) {
		super(new ViewServiceDescriptor<>(new AuthorDao(hibernateAdapter),
				prepareAuthorViewInfo(), prepareAuthorListViewInfo()), layout, "Авторы");
	}

	@NotNull
	private static ViewInfo<Author> prepareAuthorViewInfo() {
		@NotNull ViewInfo<Author> viewInfo = new ViewInfoImpl<>(Author.class);
		viewInfo.addFieldInfo("surname", new TextFieldInfo("Фамилия", 30));
		viewInfo.addFieldInfo("initials", new TextFieldInfo("Инициалы", 10));
		return viewInfo;
	}

	@NotNull
	private static ListViewInfo<Author> prepareAuthorListViewInfo() {
		@NotNull ListViewInfo<Author> listViewInfo = new ListViewInfoImpl<>(Author.class);
		listViewInfo.addColumnInfo("lowerSurname", new StringColumnInfo("Фамилия", 30));
		listViewInfo.addColumnInfo("lowerInitials", new StringColumnInfo("Инициалы", 10));
		return listViewInfo;
	}
}
