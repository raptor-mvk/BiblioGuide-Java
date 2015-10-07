/**
 * (c) raptor_MVK, 2015. All rights reserved.
 */

package ru.mvk.biblioGuide.module.db;

import org.jetbrains.annotations.NotNull;
import ru.mvk.iluvatar.module.db.HibernateAdapter;
import ru.mvk.iluvatar.module.db.SQLiteAbstractDbController;

import java.util.ArrayList;
import java.util.List;

public class BiblioGuideDbController extends SQLiteAbstractDbController {
  public BiblioGuideDbController(@NotNull HibernateAdapter hibernateAdapter) {
    super(hibernateAdapter);
  }

  @Override
  protected boolean updateDbSchema(int fromDbVersion) {
    return true;
  }

  @Override
  protected boolean createDbSchema() {
    execute("create table book (name text, lowerName text, author int, bookCase int, " +
                "shelf int);");
    execute("create table author (surname text, lowerSurname text, initials text, " +
        "lowerInitials text);");
    execute("create table bookCase (name text, lowerName text, shelvesCount int);");
    execute("create table totals (name text);");
    execute("insert into totals (name) values ('Всего')");
    return true;
  }

  @Override
  protected int getAppId() {
    return 0x17CFB65D;
  }

  @Override
  protected int getAppDbVersion() {
    return 1;
  }
}
