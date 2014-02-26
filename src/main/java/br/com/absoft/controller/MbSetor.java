package br.com.absoft.controller;

import br.com.absoft.model.dao.HibernateDAO;
import br.com.absoft.model.dao.InterfaceDAO;
import br.com.absoft.model.entities.Setor;
import br.com.absoft.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class MbSetor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Setor setor = new Setor();

    private List<Setor> setores;

    private InterfaceDAO<Setor> setorDAO() {
        InterfaceDAO<Setor> setorDAO = new HibernateDAO<Setor>(Setor.class, FacesContextUtil.getRequestSession());
        return setorDAO;
    }

    public String limpSetor() {
        setor = new Setor();
        return editSetor();
    }

    public String editSetor() {
        return "/restrict/cadastrarsetor.jsf";
    }

    public String addSetor() {
        if (setor.getIdSetor() == null || setor.getIdSetor() == 0) {
            insertSetor();
        } else {
            updateSetor();
        }
        return null;
    }

    public void insertSetor() {
        setorDAO().save(setor);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
    }

    public void updateSetor() {
        setorDAO().update(setor);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }
    
    public void deleteSetor(){
        setorDAO().remove(setor);
    }

    public MbSetor() {
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public List<Setor> getSetores() {
        setores = setorDAO().getEntities();
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }

}
