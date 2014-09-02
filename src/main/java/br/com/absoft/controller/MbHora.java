package br.com.absoft.controller;

import br.com.absoft.conversores.ConverterMinutosHora;
import br.com.absoft.model.dao.HibernateDAO;
import br.com.absoft.model.dao.InterfaceDAO;
import br.com.absoft.model.entities.Funcionario;
import br.com.absoft.model.entities.Hora;
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
public class MbHora implements Serializable {

    private static final long serialVersionUID = 1L;

    private Hora hora = new Hora();

    private Funcionario funcionario;

    private List<Hora> horas;

    private List<Hora> horas2;

    private InterfaceDAO<Hora> horaDAO() {
        InterfaceDAO<Hora> horaDAO = new HibernateDAO<Hora>(Hora.class, FacesContextUtil.getRequestSession());
        return horaDAO;
    }

    private InterfaceDAO<Funcionario> funcionarioDAO() {
        InterfaceDAO<Funcionario> funcionarioDAO = new HibernateDAO<Funcionario>(Funcionario.class, FacesContextUtil.getRequestSession());
        return funcionarioDAO;
    }

    public String limpHora() {
        hora = new Hora();
        return editHora();
    }

    public String editHora() {
        return "/restrict/registrarhora.jsf";
    }

    public String addHora() {
        if (hora.getIdHora() == null || hora.getIdHora() == 0) {
            insertHora();
        } else {
            updateHora();
        }
        return "/restrict/horaextra.jsf";
    }

    private void insertHora() {
        //Adiciona o Usuário
        hora.setUsuario(new BbUsuarioLogado().procuraPessoa().getUsuario());
        
        horaDAO().save(hora);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
        adicionaHora();

    }

    private void updateHora() {
        horaDAO().update(hora);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
    }

    private void adicionaHora() {

        //Adiciono o id do funcionário selecionado e retorno o objeto
        funcionario = funcionarioDAO().getEntity(hora.getFuncionario().getIdFuncionario());

        //Retorno os minutos do funcionario
        int minutosFuncionario = funcionario.getMinutosTotais();

        //Retorno os minutos negativos
        int minutosNegativos = funcionario.getMinutosNegativos();

        //Pega a hora do campo
        int minutosCampo = hora.getTempoMinutos();

        //Se for Banco de Horas
        if (hora.getTipo() == 'C') {

            //Adiciona e soma os minutos á hora do Funcionario
            minutosFuncionario = minutosFuncionario + minutosCampo;

            //Abate as horas Negativas
            minutosFuncionario = minutosFuncionario - minutosNegativos;

            if (minutosFuncionario < 0) {
                //Encaminha o valor negativo para o campo de minutos negativos e transforma em positivo
                minutosNegativos = minutosFuncionario * (-1);

                //zera os minutos do funcionario
                minutosFuncionario = 0;

            } else {
                //zera os minutos negativvos
                minutosNegativos = 0;
            }

            //Seta o valor dos miunutos positivos no Objeto
            funcionario.setMinutosTotais(minutosFuncionario);

            //Seta o valor dos minutos negativos no Objeto
            funcionario.setMinutosNegativos(minutosNegativos);

            //Atualiza o Funcionario
            funcionarioDAO().update(funcionario);

            //Se for compensar
        } else if (hora.getTipo() == 'D') {

            //Se a hora do funcionario - a hora do campo for maior que 0  | Abate nas horas do funcionario
            if (minutosFuncionario - minutosCampo > 0) {

                //Diminui os minutos 
                //Seta o valor no Objeto
                funcionario.setMinutosTotais(minutosFuncionario - minutosCampo);

                //Atualiza o Funcionario
                funcionarioDAO().update(funcionario);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "As horas foram compensadas no Banco de Horas!\n Horas Restantes no Banco de Horas : " + ConverterMinutosHora.converteHora_Minuto(funcionario.getMinutosTotais()), ""));
            } else {

                //Adiciona e dimiminui nos minutos restantes
                //Seta o valor no Objeto
                minutosNegativos = minutosCampo - minutosFuncionario;

                //Seta os minutos negativos
                funcionario.setMinutosNegativos(minutosNegativos);

                //Zera os minutos
                funcionario.setMinutosTotais(0);

                //Atualiza o Funcionario
                funcionarioDAO().update(funcionario);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Não há horas suficientes para abatimento total!\n O restante foi lançado em á Compensar (Horas Negativas)", ""));
            }

        }

    }

    public void deleteHora() {
        horaDAO().remove(hora);
    }

    public String detalheHora() {
        return "/restrict/horaextra.jsf";
    }

    public void verificaSetor() {
        if ("ROLE_ADMIN".equals(new BbUsuarioLogado().procuraPessoa().getPermissao())) {
            horas2 = getHoras();
        } else {
            horas2 = new ArrayList<Hora>();
            for (Hora hra : getHoras()) {

                if (new BbUsuarioLogado().procuraPessoa().getSetor().getIdSetor() == hra.getFuncionario().getSetor().getIdSetor()) {
                    horas2.add(hra);
                }
            }
        }
    }

    public MbHora() {
        verificaSetor();
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }

    public List<Hora> getHoras() {

        horas = horaDAO().getEntities();

        return horas;
    }

    public void setHoras(List<Hora> horas) {
        this.horas = horas;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Hora> getHoras2() {
        return horas2;
    }

    public void setHoras2(List<Hora> horas2) {
        this.horas2 = horas2;
    }
    
    

}
