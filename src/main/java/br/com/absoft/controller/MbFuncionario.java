package br.com.absoft.controller;

import br.com.absoft.model.dao.HibernateDAO;
import br.com.absoft.model.dao.InterfaceDAO;
import br.com.absoft.model.entities.Funcionario;
import br.com.absoft.suport.BbUsuarioLogado;
import br.com.absoft.util.FacesContextUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class MbFuncionario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Funcionario funcionario = new Funcionario();

    private List<Funcionario> funcionarios;

    private List<Funcionario> funcionarios2;
    
    private InterfaceDAO<Funcionario> funcionarioDAO() {
        InterfaceDAO<Funcionario> funcionarioDAO = new HibernateDAO<Funcionario>(Funcionario.class, FacesContextUtil.getRequestSession());
        return funcionarioDAO;
    }

    public String limpFuncionario() {
        funcionario = new Funcionario();
        //Seleciona o setor do usuário
        funcionario.setSetor(new BbUsuarioLogado().procuraPessoa().getSetor());
        return editFuncionario();
    }

    public String editFuncionario() {
        return "/restrict/cadastrarfuncionario.jsf";
    }

    public String addFuncionario() {
        if (funcionario.getIdFuncionario() == null || funcionario.getIdFuncionario() == 0) {
            insertFuncionario();
        } else {
            updateFuncionario();
        }
        return "/restrict/consultarfuncionarios.jsf";
    }

    private void insertFuncionario() {
        funcionario.setMinutosNegativos(0);
        funcionario.setMinutosTotais(0);

        funcionarioDAO().save(funcionario);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));

    }

    private void updateFuncionario() {
        funcionarioDAO().update(funcionario);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    private String deleteFuncionario() {
        funcionarioDAO().remove(funcionario);
        return null;
    }
    
    
        public void verificaSetor() {
        if ("ROLE_ADMIN".equals(new BbUsuarioLogado().procuraPessoa().getPermissao())) {
            funcionarios2 = getFuncionarios();
        } else {
            funcionarios2 = new ArrayList<Funcionario>();
            for (Funcionario fun : getFuncionarios()) {

                if (new BbUsuarioLogado().procuraPessoa().getSetor().getIdSetor() == fun.getSetor().getIdSetor()) {
                    funcionarios2.add(fun);
                }
            }
        }
    }

    public MbFuncionario() {
        verificaSetor();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getFuncionarios() {
        funcionarios = funcionarioDAO().getEntities();
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Funcionario> getFuncionarios2() {
        return funcionarios2;
    }

    public void setFuncionarios2(List<Funcionario> funcionarios2) {
        this.funcionarios2 = funcionarios2;
    }
    
    

}
