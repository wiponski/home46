package kz.attractor.java.lesson44;

import com.sun.net.httpserver.HttpExchange;
import kz.attractor.java.server.ContentType;
import kz.attractor.java.server.Cookie;
import kz.attractor.java.server.ResponseCodes;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Lesson46Server extends Lesson45Server {


    public Lesson46Server(String host, int port) throws IOException {
        super(host, port);

        registerGet("/cookie", this::cookieHandler);
        booksGet("/library", this::booksGet);
        booksPost("/library", this::booksPost);
        booksPost("/books", this::takeBook);

    }


//    private void cookieHandler(HttpExchange exchange) {
//        Cookie sessionCookie = Cookie.make("userId","123");
//        exchange.getResponseHeaders().add("Set-Cookie",sessionCookie.toString());
//
//        Map<String, Object> data = new HashMap<>();
//        int times = 42;
//        data.put("times", times);
//        renderTemplate(exchange,"cookie.html",data);
//    }


    private void cookieHandler(HttpExchange exchange) {
        //  Cookie sessionCookie = Cookie.make("userId","123");
        //  exchange.getResponseHeaders().add("Set-Cookie",sessionCookie.toString());

        Map<String, Object> data = new HashMap<>();
        String name = "times";


//        Cookie c1 = Cookie.make("user%Id","456");
//        setCookie(exchange,c1);
//
//        Cookie c2 = Cookie.make("user-mail","qwe@qwe.qwe");
//        setCookie(exchange,c2);
//
//        Cookie c3 = Cookie.make("restricted()<>@,;:\\\"/[]?={}","()<>@,;:\\\"/[]?={}");
//        setCookie(exchange,c3);

        String cookieString = getCookies(exchange);

        Map<String, String> cookies = Cookie.parse(cookieString);
        String cookieValue = cookies.getOrDefault(name, "0");
        int times = Integer.parseInt(cookieValue) + 1;

        Cookie c4 = new Cookie(name, times);
        setCookie(exchange, c4);

        data.put(name, times);
        data.put("cookies", cookies);

        renderTemplate(exchange, "cookie.html", data);
    }

    @Override
    protected void loginPost(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");
        try {
            if (parsed.size() == 2) {
                String email = parsed.get("email");
                String pass = parsed.get("user-password");


                List<UserData> data = FileService.readUsers();
                for (UserData user : data) {
                    if (user.getMail().equals(email) && user.getPass().equals(pass)) {

                        Cookie c1 = Cookie.make("email", email);
                        setCookie(exchange, c1);
                        Cookie c2 = Cookie.make("password", pass);
                        setCookie(exchange, c2);
                        Cookie c3 = Cookie.make("userNumber", new UserData(email, pass, "").getHashNum());
                        setCookie(exchange, c3);


                        redirect303(exchange, "/cookie");
                    }
                }
                throw new RuntimeException("Не корректные данные пользователя!");
            } else {
                throw new RuntimeException("\"Заполнить поля! <a href=\\\"/register\\\"> вернутся </a>\"");
            }

        } catch (RuntimeException exception) {
            try {
                sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, exception.getMessage().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    protected void booksPost(HttpExchange exchange) {

    }

    private void booksGet(HttpExchange exchange) {
        Path path = makeFilePath("library.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
        //renderTemplate(exchange,"libary.html",);
    }

    private void takeBook(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");
        int id = Integer.parseInt(parsed.get("id"));
        List<BookData> data = FileService.readBooks();
        var d = new ArrayList<>(
                List.of(data.get(0))
        );
        var s = d.get(0);
        for (int i = 0; i <= data.size(); i++) {

        }
        redirect303(exchange, "/books");
    }
}



