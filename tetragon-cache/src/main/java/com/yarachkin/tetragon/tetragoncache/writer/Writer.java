package com.yarachkin.tetragon.tetragoncache.writer;

import com.yarachkin.tetragon.tetragoncache.exception.CacheTetragonException;
import com.yarachkin.tetragon.tetragoncache.filehelper.FileHelper;
import com.yarachkin.tetragon.tetragoncache.reader.Reader;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class Writer {

    private Writer() {
    }

    public static Writer getInstance() {
        return Writer.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final Writer INSTANCE = new Writer();
    }

    public void write(List<Tetragon> tetragons) throws CacheTetragonException {
        Reader.getInstance().createFileIfNotExists();
        try (FileWriter fileWriter = new FileWriter(FileHelper.getInstance().acquireFilePath());
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Tetragon tetragon : tetragons) {
                bufferedWriter.write(tetragon + "\n");
            }
        } catch (IOException e) {
            throw new CacheTetragonException("Error in file writing.", e);
        }
    }
}
