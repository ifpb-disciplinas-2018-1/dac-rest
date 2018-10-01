package br.edu.ifpb;

import java.io.Serializable;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 01/10/2018, 08:15:43 
 * { "userId": 1, 
 * "id": 1, 
 * "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", 
 * "body": "quia et
 * suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit
 * molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet
 * architecto" }
 */
public class Post implements Serializable {

    private int userId;
    private int id;
    private String title;
    private String body;

    public Post() {
    }

    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" + "userId=" + userId + ", id=" + id + ", title=" + title + ", body=" + body + '}';
    }
    

}
