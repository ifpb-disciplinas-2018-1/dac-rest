package br.edu.ifpb;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 21/08/2018, 11:24:18
 */
public class Comentario {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    public Comentario() {
        this.name =  "Maria";
        this.email = "maria@org.com";
    }
    

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Comentario{" + "postId=" + postId + ", id=" + id + ", name=" + name + ", email=" + email + ", body=" + body + '}';
    }

}
