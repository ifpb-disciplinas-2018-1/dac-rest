package br.edu.ifpb.api;

import br.edu.ifpb.domain.Integrante;
import br.edu.ifpb.service.ServiceDeIntegrante;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/09/2018, 07:44:05
 */
@Stateless
@Path("integrantes") //http://localhost:8080/dac-rest/api/integrantes
//@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ResourcesIntegrante {

    @Inject
    private ServiceDeIntegrante service;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response todosOsIntegrantes() {
        List<Integrante> lista = this.service.todosOsIntegrantes();

        //Bag
        GenericEntity<List<Integrante>> entity = new GenericEntity<List<Integrante>>(lista) {
        };
        return Response.ok() // 200
                .entity(entity)
                .build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response salvar(Integrante integrante, @Context UriInfo uriInfo) {
        Integrante entity = this.service.salvar(integrante);
        String id = String.valueOf(entity.getId());
        URI location = uriInfo.getAbsolutePathBuilder().path(id).build();
        return Response.created(location)
                .entity(entity)
                .build();
    }

    @GET
    @Path("{id}") //http://localhost:8080/dac-rest/api/integrantes/1
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response integrante(@PathParam("id") int id) {
        Optional<Integrante> entity = this.service.integranteCom(id);

        if (!entity.isPresent()) {
            return Response.noContent()// 200
                    .build();
        }

        return Response.ok() // 200
                .entity(entity.get())
                .build();
    }
    @DELETE
    @Path("{id}") //http://localhost:8080/dac-rest/api/integrantes/1
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response removerIntegrante(@PathParam("id") int id) {
        Optional<Integrante> entity = this.service.removerIntegranteCom(id);

        if (!entity.isPresent()) {
            return Response.noContent()// 200
                    .build();
        }

        return Response.ok() // 200
                .entity(entity.get())
                .build();
    }
}
