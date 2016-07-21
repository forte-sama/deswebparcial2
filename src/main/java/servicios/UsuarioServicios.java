package servicios;

import main.EntityManagerCRUD;
import modelos.Marca;
import modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saleta on 6/24/2016.
 */
public class UsuarioServicios extends EntityManagerCRUD<Usuario> {
    private static UsuarioServicios instancia;

    private UsuarioServicios() {
        super(Usuario.class);
    }

    public static UsuarioServicios getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioServicios();
        }
        return instancia;
    }

    public List<Usuario> findNoAutorizado() {
        List<Usuario> resp = new ArrayList<>();

        EntityManager em = getEntityManager();

        //do the thing
        try {
            //do the exact thing
            String str_query = "SELECT u FROM Usuario u WHERE u.isAutorizado = false";
            TypedQuery<Usuario> query = em.createQuery(str_query, Usuario.class);

            resp = query.getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

        return resp;
    }

    public void crearAdminDefault() {
        Usuario admin = new Usuario();
        admin.setAdmin(true);
        admin.setAutorizado(true);
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setEmail("admin@sitio.com");
        admin.setNombre("admin");
        admin.setTelefono("809-555-5556");
        admin.setCelular("809-555-5555");
        admin.setDireccion("direccion admin");

        this.edit(admin);
    }
}