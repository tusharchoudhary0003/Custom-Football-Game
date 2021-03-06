package com.vungle.warren.persistence;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface Persistor {

    public interface MigrationCallback {
        void onDowngrade(int i, int i2);

        void onUpgrade(int i, int i2);
    }

    public interface Transformation<T> {
        T transform(int i, int i2, byte[] bArr);
    }

    void clearCache();

    boolean delete(Memorable memorable);

    <T extends Memorable> List<T> extractAll(Class<T> cls);

    <T extends Memorable> T extractFrom(File file, Class<T> cls) throws IOException;

    <T extends Memorable> T find(String str, Class<T> cls);

    <T extends Memorable> List<T> findAll(Set<String> set, Class<T> cls);

    File getStorageDirectory() throws IllegalStateException;

    void init(int i, MigrationCallback migrationCallback);

    <T extends Memorable> void migrateData(int i, int i2, Class<T> cls, Transformation<T> transformation);

    boolean save(Memorable memorable);

    boolean save(List<Memorable> list);
}
