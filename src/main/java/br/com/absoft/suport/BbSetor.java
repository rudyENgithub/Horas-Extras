package br.com.absoft.suport;

import br.com.absoft.model.dao.HibernateDAO;
import br.com.absoft.model.dao.InterfaceDAO;
import br.com.absoft.model.entities.Setor;
import br.com.absoft.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "bbSetor")
@RequestScoped
public class BbSetor implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Setor> setores;

    public List<Setor> getSetores() {
        InterfaceDAO<Setor> setoresDAO = new HibernateDAO<Setor>(Setor.class, FacesContextUtil.getRequestSession());
        return setoresDAO.getEntities();
    }

    public boolean getCampoAtivado() {
        boolean retorno;
        retorno = !"ROLE_ADMIN".equals(new BbUsuarioLogado().procuraPessoa().getPermissao());
        return retorno;

    }
}
