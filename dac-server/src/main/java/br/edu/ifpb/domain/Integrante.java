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
    private String sexo;

    public Integrante() {
    }

    public Integrante(int id, String nome, String sexo) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
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
        return String.format("https://randomuser.me/api/portraits/%s/%d.jpg", sexo, id);
//                "large": "https://randomuser.me/api/portraits/men/47.jpg"
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Integrante other = (Integrante) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    

}
