package com.yarachkin.tetragon.tetragoncache.writer;

import com.yarachkin.tetragon.tetragoncache.cache.Cache;
import com.yarachkin.tetragon.tetragoncache.exception.CacheTetragonException;
import com.yarachkin.tetragon.tetragoncache.filehelper.FileHelper;
import com.yarachkin.tetragon.tetragoncache.reader.Reader;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private Writer() {
    }

    public static Writer getInstance() {
        return Writer.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final Writer INSTANCE = new Writer();
    }

    public void write() throws CacheTetragonException {
        Reader.getInstance().createFileIfNotExists();
        try (FileWriter fileWriter = new FileWriter(FileHelper.getInstance().acquireFilePath());
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Tetragon tetragon : Cache.getInstance().getCache()) {
                bufferedWriter.write(tetragon + "\n");
            }
        } catch (IOException e) {
            throw new CacheTetragonException("Error in file writing.", e);
        }
    }
}
