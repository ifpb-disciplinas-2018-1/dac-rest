# Consumindo uma API RESTful

Para consumirmos uma API RESTful necessitamos trabalhar com a classe `Client`. Uma das maneiras de termos acesso a uma instância:  
`Client client = ClientBuilder.newClient();`.

A classe *Client* é o principal ponto de entrada para a API usada para criar e executar requisições e consumir as respostas retornadas.

Para identificarmos o recurso que vamos consumir utilizamos a classe `WebTarget`. A classe *WebTarget* identifica um recurso definido por uma URI. Recuperamos uma instância utilizando:

```
String uri = "https://jsonplaceholder.typicode.com";
WebTarget root = client.target(uri);
```

Para executarmos uma requisição ao recurso definido na URI utilizando o método *POST*, fazemos da seguinte forma:

```
WebTarget posts = root.path("posts");
Response response = posts.request().get();
String json = response.readEntity(String.class);
```

Após a execução dessa sequência de métodos temos a resposta em uma `String`. Neste exemplo, a resposta está no formato `Json`.

Caso a resposta retornasse um array de objetos, podemos executar:

```
JsonReader reader = Json.createReader(new StringReader(json));
JsonArray array = reader.readArray();
```

Podemos percorrer esse Array utilizando:

```
JsonReader reader = Json.createReader(new StringReader(json));
JsonArray array = reader.readArray();
JsonArray collect = array.stream()
                .filter(t -> t.asJsonObject().getInt("id") == 1)
                .collect(JsonCollectors.toJsonArray());
```

Para recuperarmos um comentários especifico, precisamos executar:

```
int idComment = 1;
WebTarget comments = root.path("comments").path("{id}").resolveTemplate("id", idComment);
Response response = comments.request().get();
String json = response.readEntity(String.class);
```

Utilizando a JSON-B API podemos trabalhar com conversão de objetos para o formato Json, e o inverso. Podemos, por exemplo, criar um objeto `Comentario` utilizando a resposta da execução anterior.

```
Comentario comentario = JsonbBuilder.create()
                .fromJson(json, Comentario.class);
```

Caso a resposta da execução nos retornasse um Array de objetos, podemos executar:

```
ArrayList<Comentario> collect = JsonbBuilder.create()
                .fromJson(body, 
                        new ArrayList<Comentario>(){}
                        .getClass().getGenericSuperclass()
                );
        collect.forEach(c->System.out.println(c.getName()));
```        

Fazendo uso a JSON-P, temos uma alternativa para acessarmos as propriedades de um arquivo no formato Json sem iterar sobre o array:

```
JsonPointer pointer = Json.createPointer("/0/title");
JsonValue values = pointer.getValue(
                Json.createReader(
                        new StringReader(json)
                ).readObject()
        );
```

Neste exemplo conseguimos recuperar da lista de comentário, na posição `0`, o `title` do comentário. Poderíamos fazer, também, como:

```
JsonPointer pointer = Json.createPointer("/0/title");
JsonValue values = pointer.getValue(array);
```

Caso seja necessário alterarmos (ou inserir) as informações do arquivo, podemos fazer: 

```        
JsonArray newJsonObject = pointer.replace(
                array, Json.createValue("Ice Cream")
);
//System.out.println(newJsonObject);
JsonArray jsonObject = pointer.add( 
                array, Json.createValue("Java EE 8")
);
System.out.println(jsonObject);
``` 

Para utilizarmos as API's descritas precisamos adicionar ao `pom.xml` as seguintes configurações:
```
<dependencies>  
        <!--REST Client-->
        <dependency>
            <groupId>org.glassfish.jersey.core</groupId>
            <artifactId>jersey-client</artifactId>
            <version>2.23</version>
        </dependency>
        <!--JSON-P-->
        <dependency>
            <groupId>org.glassfish</groupId>
            <artifactId>javax.json</artifactId>
            <version>1.1</version>
        </dependency>
        <!--JSON-B-->
        <dependency>
            <groupId>org.eclipse</groupId>
            <artifactId>yasson</artifactId>
            <version>1.0</version>
        </dependency>
    </dependencies>
```
