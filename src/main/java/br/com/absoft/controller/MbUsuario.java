package br.com.absoft.controller;

import br.com.absoft.conversores.ConverterSHA1;
import br.com.absoft.model.dao.HibernateDAO;
import br.com.absoft.model.dao.InterfaceDAO;
import br.com.absoft.model.entities.Usuario;
import br.com.absoft.suport.BbSetor;
import br.com.absoft.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class MbUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuario = new Usuario();

    private List<Usuario> usuarios;

    private String confereSenha;

    String retorno = null;

    private InterfaceDAO<Usuario> usuarioDAO() {
        InterfaceDAO<Usuario> usuarioDAO = new HibernateDAO<Usuario>(Usuario.class, FacesContextUtil.getRequestSession());
        return usuarioDAO;
    }

    public String limpUsuario() {
        usuario = new Usuario();        
        return editUsuario();
    }

    public String editUsuario() {
        return "/restrict/cadastrarusuario.jsf";
    }

    public String addUsuario() {

        if (usuario.getIdUsuario() == null || usuario.getIdUsuario() == 0) {
            insertUsuario();
        } else {
            updateUsuario();
        }
        return retorno;
    }

    private void insertUsuario() {

        if (usuario.getSenha() == null ? ConverterSHA1.cipher(confereSenha) == null : usuario.getSenha().equals(ConverterSHA1.cipher(confereSenha))) {

            usuarioDAO().save(usuario);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
            retorno = "/restrict/consultarusuarios.jsf";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "As senhas não conferem !", ""));
        }
    }

    private void updateUsuario() {
        usuarioDAO().update(usuario);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    private void deleteUsuario() {
        usuarioDAO().remove(usuario);

    }

    public MbUsuario() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        usuarios = usuarioDAO().getEntities();
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getConfereSenha() {
        return confereSenha;
    }

    public void setConfereSenha(String confereSenha) {
        this.confereSenha = confereSenha;
    }

}
