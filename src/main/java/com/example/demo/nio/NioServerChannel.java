package com.example.demo.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author 牛朋朋
 * @date 2019/4/13
 */
public class NioServerChannel extends Thread {


    @Override
    public void run() {

        try {
            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8880));
            serverSocketChannel.configureBlocking(false);
            //注册到selector上 并说明关注点
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                //等待就绪的channel
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey =  iterator.next();
                    sayHello((ServerSocketChannel)selectionKey.channel());
                    selectionKey.channel();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void sayHello(ServerSocketChannel serverSocketChannel){
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.write(Charset.defaultCharset().encode("hello"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
