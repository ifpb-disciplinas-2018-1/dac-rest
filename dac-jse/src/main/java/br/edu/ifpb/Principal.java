package br.edu.ifpb;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonPointer;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.stream.JsonCollectors;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 30/09/2018, 19:41:53
 */
public class Principal {

    public static void main(String[] args) {

//        consumindoPosts();
//        consumindoPostComID();
//        consumindoPostsConvetendo();
//        consumindoComentarios();
//        consumindoVagas();
        String uri = "https://pokeapi.co/api/v2/pokemon/1/";
        Client client = ClientBuilder.newClient();
        WebTarget root = client.target(uri); // root
        String body = root.request().get(String.class);
        JsonReader reader = Json.createReader(
                new StringReader(body)
        );
        JsonObject array = reader.readObject();
        JsonPointer pointer = Json.createPointer("/abilities/0/ability/url");
        JsonValue value = pointer.getValue(array);
        System.out.println("value = " + value);
        
        // http://localhost:8080/dac-server/api/integrantes
        // manipulaJSONB();
        // manipularJson();
    }

    private static void consumindoVagas() {
        String uri = "http://www.pyjobs.com.br/api";
        Client client = ClientBuilder.newClient();
        WebTarget root = client.target(uri); // root
        WebTarget jobs = root.path("jobs");
        String body = jobs.request().get(String.class);
        JsonReader reader = Json.createReader(
                new StringReader(body)
        );
        JsonObject array = reader.readObject();
        JsonPointer pointer = Json.createPointer("/objects/0/company_name");
        JsonValue value = pointer.getValue(array);
        System.out.println("value = " + value);

        JsonObject newJsonObject = pointer.replace(
                array, Json.createValue("Ice Cream")
        );

        JsonPointer pointerArray = Json.createPointer("/objects");
        JsonValue valueArray = pointerArray.getValue(newJsonObject);
        JsonArray asJsonArray = valueArray.asJsonArray();

        System.out.println("valueArray = " + valueArray);
    }

    private static void consumindoComentarios() {
        String uri = "https://jsonplaceholder.typicode.com";
        Client client = ClientBuilder.newClient();
        WebTarget root = client.target(uri); // root
        //https://jsonplaceholder.typicode.com/posts/1/comments
        // email do primeiro comentario do post com id igual a 1
        WebTarget comments = root.path("posts").path("1").path("comments");
        String body = comments.request().get(String.class);
//        System.out.println("body = " + body);
        JsonReader reader = Json.createReader(
                new StringReader(body)
        );
        JsonArray array = reader.readArray();
        String email = array.get(0).asJsonObject().getString("email");
//        System.out.println("email = " + email);
        JsonPointer pointer = Json.createPointer("/0/email");
        JsonValue value = pointer.getValue(array);
        System.out.println("value = " + value);
    }

    private static void consumindoPostsConvetendo() {
        String uri = "https://jsonplaceholder.typicode.com";
        Client client = ClientBuilder.newClient();
        WebTarget root = client.target(uri); // root
        //https://jsonplaceholder.typicode.com/posts/
        String body = root.path("posts")
                .request()
                .get(String.class);

//        System.out.println("body = " + body);
        ArrayList<Post> posts = null;

        JsonArray array = Json.createReader(
                new StringReader(body)
        ).readArray();

        Jsonb create = JsonbBuilder.create();
//        List<Post> collect = array.stream()
//                .map(value->value.asJsonObject())
//                .map(obj->create.fromJson(obj.toString(), Post.class))
//                .collect(Collectors.toList());
//        collect.forEach(System.out::println);

//        ArrayList<Post> collect = create
//                .fromJson(body, new ArrayList<Post>() {
//                }.getClass()
//                        .getGenericSuperclass());
        //        collect.forEach(System.out::println);
        Post[] fromJson = create.fromJson(body, Post[].class);
        List<Post> collect = Arrays.asList(fromJson);
        collect.forEach(System.out::println);

//        collect.forEach(System.out::println);
    }

    private static void consumindoPostComID() {
        //https://jsonplaceholder.typicode.com/posts/1
        String uri = "https://jsonplaceholder.typicode.com";
        Client client = ClientBuilder.newClient();
        WebTarget root = client.target(uri); // root
        int idPost = 1;
        WebTarget postsId = root.path("posts").path(String.valueOf(idPost));
        Response response = postsId.request().get();
        String body = response.readEntity(String.class);
        Jsonb create = JsonbBuilder.create();
        Post post = create.fromJson(body, Post.class);
        System.out.println("post = " + post);

    }

    private static void consumindoPosts() {
//https://jsonplaceholder.typicode.com/posts/
        String uri = "https://jsonplaceholder.typicode.com";
        Client client = ClientBuilder.newClient();
        WebTarget root = client.target(uri); // root
        WebTarget posts = root.path("posts");
        Response response = posts.request().get();

        String body = response.readEntity(String.class);
//        System.out.println("body = " + body);

        JsonReader reader = Json.createReader(new StringReader(body));
        JsonArray array = reader.readArray();
//        System.out.println("readObject = " + array);

        /*
        {
        "userId": 1,
        "id": 1,
        "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        }
         */
        //userId==1
        JsonArray collect = array.stream()
                .filter(value -> value.asJsonObject().getInt("userId") == 1)
                .collect(JsonCollectors.toJsonArray());

        collect.forEach(System.out::println);
    }

    private static void manipulaJSONB() {
        int idPost = 2;
//        WebTarget comments = root.path("posts/{idPost}/comments")
//                .resolveTemplate("idPost", idPost);
//        Response response = comments.request().get();
//        String body = response.readEntity(String.class);
//        System.out.println(body);
//        JsonArray array = Json.createReader(
//                new StringReader(body)
//        ).readArray();

//        ArrayList<Comentario> collect = JsonbBuilder.create()
//                .fromJson(body,
//                        new ArrayList<Comentario>() {
//                        }
//                                .getClass().getGenericSuperclass()
//                );
//        collect.forEach(c -> System.out.println(c.getName()));
//        List<Comentario> collect = array.stream()
//                .map(new Function<JsonValue, Comentario>() {
//                    @Override
//                    public Comentario apply(JsonValue t) {
//                        
//                        JsonObject j = t.asJsonObject();
//
//                        
//                    }
//                }).collect(Collectors.toList());
//        collect.forEach(System.out::println);
//        System.out.println(array);
    }

    private static void manipularJson() {
        String uri = "https://jsonplaceholder.typicode.com";
        Client client = ClientBuilder.newClient();
        WebTarget root = client.target(uri);
        WebTarget posts = root.path("posts");
//        int idComment = 1;
//        WebTarget comments = root.path("comments").path("{idComment}")
//                .resolveTemplate("idComment", idComment);
//        Response response = comments.request().get();
//        String json = response.readEntity(String.class);
//        Comentario comentario = JsonbBuilder.create()
//                .fromJson(json, Comentario.class);
//        System.out.println(comentario);

        Response response = posts.request().get();
        String json = response.readEntity(String.class);
//        System.out.println(json);
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonArray array = reader.readArray();
//        System.out.println(array);
//        JsonArray collect = array.stream()
//                .filter(t -> t.asJsonObject().getInt("id") == 1)
//                .collect(JsonCollectors.toJsonArray());
//        System.out.println(collect);
//
//        JsonPointer pointer = Json.createPointer("/0/title");
//        JsonValue values = pointer.getValue(
//                Json.createReader(
//                        new StringReader(json)
//                ).readObject()
//        );

//        JsonValue values = pointer.getValue(
//                array
//        );
//        System.out.println(values);
//        JsonArray newJsonObject = pointer.replace(
//                array, Json.createValue("Ice Cream")
//        );
//        System.out.println(newJsonObject);
//        JsonArray jsonObject = pointer.add(
//                array, Json.createValue("Java EE 8")
//        );
//        System.out.println(jsonObject);
    }
}
