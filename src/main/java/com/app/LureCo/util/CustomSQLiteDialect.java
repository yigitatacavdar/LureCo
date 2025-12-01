package com.app.LureCo.util;

import org.hibernate.community.dialect.SQLiteDialect;

import org.hibernate.dialect.identity.IdentityColumnSupport;

public class CustomSQLiteDialect extends SQLiteDialect {

    private static final IdentityColumnSupport IDENTITY_SUPPORT = new SQLiteAutoIncrementIdentityColumnSupport();

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return IDENTITY_SUPPORT;
    }


    public static class SQLiteAutoIncrementIdentityColumnSupport extends org.hibernate.community.dialect.identity.SQLiteIdentityColumnSupport {
        @Override
        public String getIdentityColumnString(int type) {
            return "integer primary key autoincrement";
        }
    }
}