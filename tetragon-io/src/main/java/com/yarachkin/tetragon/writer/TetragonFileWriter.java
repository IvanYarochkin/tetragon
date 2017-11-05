package com.yarachkin.tetragon.writer;

import com.yarachkin.tetragon.entity.Tetragon;
import com.yarachkin.tetragon.exception.IOTetragonException;
import com.yarachkin.tetragon.filehelper.TetragonFileHelper;
import com.yarachkin.tetragon.reader.TetragonFileReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class TetragonFileWriter {

    private TetragonFileWriter() {
    }

    public static TetragonFileWriter getInstance() {
        return TetragonFileWriter.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final TetragonFileWriter INSTANCE = new TetragonFileWriter();
    }

    public void write(List<Tetragon> tetragons) throws IOTetragonException {
        TetragonFileReader.getInstance().createFileIfNotExists();
        try (FileWriter fileWriter = new FileWriter(TetragonFileHelper.getInstance().acquireFilePath());
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Tetragon tetragon : tetragons) {
                bufferedWriter.write(tetragon + "\n");
            }
        } catch (IOException e) {
            throw new IOTetragonException("Error in file writing.", e);
        }
    }
}
