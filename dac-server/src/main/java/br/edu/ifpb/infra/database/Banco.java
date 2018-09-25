package br.edu.ifpb.infra.database;

import br.edu.ifpb.domain.Integrante;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/09/2018, 07:38:20
 */
//@Stateless
//@Stateful
@Singleton
@Startup
public class Banco {

    private final List<Integrante> integrantes = new ArrayList<>();
    private final AtomicInteger count = new AtomicInteger(4);

    @PostConstruct
    public void init() {
        this.integrantes.add(new Integrante(1, "Chaves","https://randomuser.me/api/portraits/men/1.jpg"));
        this.integrantes.add(new Integrante(2, "Kiko","https://randomuser.me/api/portraits/men/2.jpg"));
        this.integrantes.add(new Integrante(3, "Madruga","https://randomuser.me/api/portraits/men/3.jpg"));
        this.integrantes.add(new Integrante(4, "Florinda","https://randomuser.me/api/portraits/women/4.jpg"));

    }

    public Integrante salvar(Integrante integrante) {
        integrante.setId(count.incrementAndGet());
        this.integrantes.add(integrante);
        return integrante;
    }

    public List<Integrante> todosOsIntegrantes() {
        return Collections.unmodifiableList(integrantes);
    }

    public void removerIntegrante(Integrante integrante) {
        this.integrantes.remove(integrante);
    }

}
