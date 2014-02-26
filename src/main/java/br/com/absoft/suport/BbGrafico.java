package br.com.absoft.suport;

import br.com.absoft.model.dao.HibernateDAO;
import br.com.absoft.model.dao.InterfaceDAO;
import br.com.absoft.model.entities.Funcionario;
import br.com.absoft.model.entities.Setor;
import br.com.absoft.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;

@ManagedBean(name = "bbGrafico")
@RequestScoped
public class BbGrafico implements Serializable {

    private static final long serialVersionUID = 1L;

    private CartesianChartModel graficoPorSetor;

    private List<Funcionario> funcionarios;

    private Setor setor = new Setor();

    public List<Funcionario> getFuncionarios() {
        InterfaceDAO<Funcionario> funcionarioDAO = new HibernateDAO<Funcionario>(Funcionario.class, FacesContextUtil.getRequestSession());
        return funcionarioDAO.getEntities();

    }

    public void criarGraficoSetor() {
        graficoPorSetor = new CartesianChartModel();
        BarChartSeries series1 = new BarChartSeries();
        series1.setLabel("Banco de Horas(Crédito)");

        BarChartSeries series2 = new BarChartSeries();
        series2.setLabel("Á Compensar (Débito)");

        for (Funcionario fun : getFuncionarios()) {
            if (setor.getIdSetor() == null || setor.getIdSetor() == 0) {
                if (new BbUsuarioLogado().procuraPessoa().getSetor().getIdSetor() == fun.getSetor().getIdSetor()) {
                    series1.set(fun.getNome(), (double) fun.getMinutosTotais() / 60);
                    series2.set(fun.getNome(), (double) fun.getMinutosNegativos() / 60);
                }

            } else if (fun.getSetor().getIdSetor() == setor.getIdSetor()) {
                series1.set(fun.getNome(), (double) fun.getMinutosTotais() / 60);
                series2.set(fun.getNome(), (double) fun.getMinutosNegativos() / 60);

            }

        }

        graficoPorSetor.addSeries(series1);
        graficoPorSetor.addSeries(series2);

    }

    public BbGrafico() {
        criarGraficoSetor();
    }

    public CartesianChartModel getGraficoPorSetor() {
        return graficoPorSetor;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }



}
