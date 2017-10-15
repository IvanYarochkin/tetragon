package com.yarachkin.tetragon.tetragonservice;

import com.yarachkin.tetragon.tetragonservice.service.impl.TetragonServiceImpl;

public class Mai {
    public static void main(String[] args) {
        TetragonServiceImpl tetragonService = new TetragonServiceImpl();
        tetragonService.findAll().forEach(System.out::println);
    }
}
