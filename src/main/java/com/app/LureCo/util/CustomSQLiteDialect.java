package com.app.LureCo.util;

import org.hibernate.community.dialect.SQLiteDialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

public class CustomSQLiteDialect extends SQLiteDialect {

    private static final IdentityColumnSupport IDENTITY_SUPPORT = new SQLiteAutoIncrementIdentityColumnSupport();

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return IDENTITY_SUPPORT;
    }

    public static class SQLiteAutoIncrementIdentityColumnSupport extends IdentityColumnSupportImpl {

        @Override
        public boolean supportsIdentityColumns() {
            return true;
        }

        @Override
        public String getIdentityColumnString(int type) {
            return "integer primary key autoincrement";
        }

        @Override
        public String getIdentitySelectString(String table, String column, int type) {
            return "select last_insert_rowid()";
        }
    }
}
