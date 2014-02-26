package br.com.absoft.suport;

import br.com.absoft.model.dao.HibernateDAO;
import br.com.absoft.model.dao.InterfaceDAO;
import br.com.absoft.model.entities.Funcionario;
import br.com.absoft.util.FacesContextUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "bbFuncionario")
@RequestScoped
public class BbFuncionario implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Funcionario> funcionarios;
    private List<Funcionario> funcionarios2;

    public List<Funcionario> getFuncionarios() {
        InterfaceDAO<Funcionario> funcionarioDAO = new HibernateDAO<Funcionario>(Funcionario.class, FacesContextUtil.getRequestSession());
        return funcionarioDAO.getEntities();

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

    public List<Funcionario> getFuncionarios2() {
        return funcionarios2;
    }

    public void setFuncionarios2(List<Funcionario> funcionarios2) {
        this.funcionarios2 = funcionarios2;
    }

    public BbFuncionario() {
        verificaSetor();
    }

}
