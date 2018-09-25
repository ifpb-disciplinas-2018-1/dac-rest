package br.edu.ifpb.domain;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/09/2018, 07:31:24
 */
@XmlRootElement
public class Integrante implements Serializable {

    public static Integrante fake() {
        return new Integrante(-1, "", "");
    }

    private int id;
    private String nome;
    private String foto;

    public Integrante() {
    }

    public Integrante(int id, String nome, String foto) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
