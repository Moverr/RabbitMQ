/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helloworld;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author muyin
 */
public class App {

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Interesting");
        new Send().sendNotification();
    }

}
