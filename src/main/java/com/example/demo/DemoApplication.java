package com.example.demo;

import com.example.demo.nio.NioServerChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author ppniu
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        NioServerChannel nioServerChannel = new NioServerChannel();
        nioServerChannel.start();

        try {
            Socket client = new Socket(InetAddress.getLocalHost(),8880);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            bufferedReader.lines().forEach(str -> System.out.println(str));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
