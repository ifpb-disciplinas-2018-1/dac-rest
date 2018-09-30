package br.edu.ifpb.infra.database;

import br.edu.ifpb.domain.Integrante;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
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

    private List<Integrante> integrantes = new ArrayList<>();
    private final AtomicInteger count = new AtomicInteger(4);

    @PostConstruct
    public void init() {
        this.integrantes.add(new Integrante(1, "Chaves", "men"));
        this.integrantes.add(new Integrante(2, "Kiko", "men"));
        this.integrantes.add(new Integrante(3, "Madruga", "men"));
        this.integrantes.add(new Integrante(4, "Florinda", "women"));

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

    public void atualizarIntegrante(int id, Integrante get) {
        this.integrantes.removeIf(integrante -> integrante.getId() == id);
        this.integrantes.add(get);
    }

}
