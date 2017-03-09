package com.warrantix.main.common.localdb;

import com.warrantix.main.WarrantixApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

public class WarrantixLocalDatabase {

    static public Realm getMyProductsDatabase() {
        Realm myProductRealm = Realm.getInstance(
                new RealmConfiguration.Builder(WarrantixApplication.getAppContext())
                        .name("myProducts.realm")
                        .migration(new RealmMigration() {
                            @Override
                            public long execute(Realm realm, long version) {
                                return 0;
                            }
                        })
                        .build());
        return myProductRealm;
    }

    static public Realm getCartProductsDatabase() {
        Realm cartProductRealm = Realm.getInstance(
                new RealmConfiguration.Builder(WarrantixApplication.getAppContext())
                        .name("cartProducts.realm")
                        .migration(new RealmMigration() {
                            @Override
                            public long execute(Realm realm, long version) {
                                return 0;
                            }
                        })
                        .build());
        return cartProductRealm;
    }

    static public Realm getChatMessagesDatabase() {
        Realm chatRealm = Realm.getInstance(
                new RealmConfiguration.Builder(WarrantixApplication.getAppContext())
                        .name("chatMessages.realm")
                        .migration(new RealmMigration() {
                            @Override
                            public long execute(Realm realm, long version) {
                                return 0;
                            }
                        })
                        .build());
        return chatRealm;
    }

    static public Realm getBrandDatabase() {
        return Realm.getInstance(
                new RealmConfiguration.Builder(WarrantixApplication.getAppContext())
                        .name("brands.realm")
                        .migration(new RealmMigration() {
                            @Override
                            public long execute(Realm realm, long version) {
                                return 0;
                            }
                        })
                        .build());
    }


}