package com.andreldm.usb_barcode_reader.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.andreldm.usb_barcode_reader.server.keyboard.Keyboard;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class Server extends NanoHTTPD {
    private Keyboard keyboard;

    public Server(Keyboard keyboard) {
        super(55599);
        this.keyboard = keyboard;
    }

    public void start() {
        try {
            start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response serve(IHTTPSession session) {
        if (!Method.POST.equals(session.getMethod())) {
            return newFixedLengthResponse(Status.METHOD_NOT_ALLOWED, MIME_PLAINTEXT, "");
        }

        if (!"/command".equals(session.getUri())) {
            return newFixedLengthResponse(Status.NOT_FOUND, MIME_PLAINTEXT, "");
        }

        try {
            Map<String, String> files = new HashMap<>();
            session.parseBody(files);

            // Remove "#!#" padding, sent in order to avoid trim during
            // HTTPSession#parseBody.
            String text = files.get("postData").replaceAll("#!#", "");
            System.out.println(text);
            keyboard.type(text);
        } catch (Exception e) {
            e.printStackTrace();
            return newFixedLengthResponse(Status.INTERNAL_ERROR, MIME_PLAINTEXT, "");
        }

        return newFixedLengthResponse(Status.OK, MIME_PLAINTEXT, "0");
    }
}
