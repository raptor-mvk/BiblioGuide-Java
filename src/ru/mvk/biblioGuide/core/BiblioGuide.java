/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.biblioGuide.core;

import javafx.application.Application;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jetbrains.annotations.NotNull;
import ru.mvk.biblioGuide.model.Author;
import ru.mvk.biblioGuide.model.Book;
import ru.mvk.biblioGuide.model.BookCase;
import ru.mvk.biblioGuide.module.db.BiblioGuideDbController;
import ru.mvk.biblioGuide.service.AuthorViewService;
import ru.mvk.biblioGuide.service.BookCaseViewService;
import ru.mvk.biblioGuide.service.BookViewService;
import ru.mvk.iluvatar.exception.IluvatarRuntimeException;
import ru.mvk.iluvatar.javafx.layout.JFXLayout;
import ru.mvk.iluvatar.javafx.layout.JFXTabViewWindowLayout;
import ru.mvk.iluvatar.module.db.DbController;
import ru.mvk.iluvatar.module.db.HibernateAdapter;
import ru.mvk.iluvatar.module.db.HibernateAdapterImpl;
import ru.mvk.iluvatar.properties.BundleStringSupplier;
import ru.mvk.iluvatar.service.ViewService;

import java.util.Locale;
import java.util.Properties;

public class BiblioGuide extends Application {
	@NotNull
	private static final String DB_FILENAME = "BiblioGuide.sqlite";
	@NotNull
	private final JFXLayout layout;
	@NotNull
	private final ViewService<Book> bookViewService;
	@NotNull
	private final ViewService<Author> authorViewService;
	@NotNull
	private final ViewService<BookCase> bookCaseViewService;
	@NotNull
	private final HibernateAdapter hibernateAdapter;
	@NotNull
	private final DbController biblioGuideDbController;
	@NotNull
	private final BundleStringSupplier stringSupplier =
			new BundleStringSupplier("BiblioGuide");

	public BiblioGuide() {
		layout = new JFXTabViewWindowLayout(820, 400);
		@NotNull Locale ruLocale = new Locale("RU");
		stringSupplier.registerLocale(ruLocale);
		layout.setStringSupplier(stringSupplier);
		hibernateAdapter = prepareHibernateAdapter();
		biblioGuideDbController = new BiblioGuideDbController(hibernateAdapter);
		if (!biblioGuideDbController.isDbSuitable()) {
			biblioGuideDbController.createDb();
		} else {
			if (!biblioGuideDbController.updateDb()) {
				throw new IluvatarRuntimeException("BiblioGuide: Could not update database");
			}
		}
		bookViewService = new BookViewService(hibernateAdapter, layout);
		authorViewService = new AuthorViewService(hibernateAdapter, layout);
		bookCaseViewService = new BookCaseViewService(hibernateAdapter, layout);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(@NotNull Stage primaryStage) throws Exception {
		bookViewService.showListView();
		layout.setStage(primaryStage);
		layout.show(960, 460);
	}

	@Override
	public void stop() throws Exception {
		System.exit(0);
	}

	private HibernateAdapter prepareHibernateAdapter() {
		@NotNull SessionFactory sessionFactory = prepareSessionFactory();
		return new HibernateAdapterImpl(sessionFactory);
	}

	@NotNull
	private SessionFactory prepareSessionFactory() {
		@NotNull Configuration configuration = prepareConfiguration();
		@NotNull Properties properties = configuration.getProperties();
		@NotNull ServiceRegistry serviceRegistry =
				new StandardServiceRegistryBuilder().applySettings(properties).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	@NotNull
	private Configuration prepareConfiguration() {
		@NotNull Configuration configuration = new Configuration();
		@NotNull Properties properties = new Properties();
		properties.put("hibernate.connection.driver_class", "org.sqlite.JDBC");
		properties.put("hibernate.connection.url", "jdbc:sqlite:" + DB_FILENAME);
		properties.put("hibernate.current_session_context_class", "thread");
		properties.put("hibernate.dialect", "ru.mvk.service.hibernate.SQLiteDialect");
		configuration.setProperties(properties);
		configuration.addAnnotatedClass(Book.class);
		configuration.addAnnotatedClass(Author.class);
		configuration.addAnnotatedClass(BookCase.class);
		return configuration;
	}
}
