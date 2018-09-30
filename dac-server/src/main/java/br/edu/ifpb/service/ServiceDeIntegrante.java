package br.edu.ifpb.service;

import br.edu.ifpb.domain.Integrante;
import br.edu.ifpb.infra.database.Banco;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/09/2018, 07:35:58
 */
@Stateless
public class ServiceDeIntegrante {

    @Inject
    private Banco dao;

    public Integrante salvar(Integrante integrante) {
        return this.dao.salvar(integrante);
    }

    public List<Integrante> todosOsIntegrantes() {
        return this.dao.todosOsIntegrantes();
    }

    public Optional<Integrante> integranteCom(int id) {
        return this.todosOsIntegrantes()
                .stream()
                .filter(integrante -> integrante.getId() == id)
                .findFirst();
//                .orElse(Integrante.fake());
    }

    public Optional<Integrante> removerIntegranteCom(int id) {
        Optional<Integrante> integrante = integranteCom(id);

        if (integrante.isPresent()) {
            this.dao.removerIntegrante(integrante.get());
        }

        return integrante;

    }

    public Optional<Integrante> atualizarIntegranteCom(int id, Integrante integrante) {
        Optional<Integrante> retorno = integranteCom(id);

        if (retorno.isPresent()) {
            this.dao.atualizarIntegrante(id, integrante);
        }

        return Optional.of(integrante);
    }
}
