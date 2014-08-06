package br.com.absoft.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdFuncionario")
    private Integer idFuncionario;

    @Column(name = "Nome", nullable = false, length = 90)
    private String nome;

    @Column(name = "Funcao", nullable = false, length = 45)
    private String funcao;

    @Column(name = "RespSetor", nullable = false)
    private char respSetor;

    @Column(name = "MinutosTotais", nullable = true)
    private Integer MinutosTotais;

    @Column(name = "MinutosNegativos", nullable = true)
    private Integer MinutosNegativos;

    @Column(name = "Rfid", nullable = true)
    private Long rfid;

    //Muitos para 1
    @ManyToOne(optional = false)
    @ForeignKey(name = "FuncionarioSetor")
    @JoinColumn(name = "IdSetor", referencedColumnName = "IdSetor")
    private Setor setor;

    //1 para muitos
    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY)
    @ForeignKey(name = "FuncionarioHora")
    private List<Hora> horas;

    public Funcionario() {
        setor = new Setor();
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public char getRespSetor() {
        return respSetor;
    }

    public void setRespSetor(char respSetor) {
        this.respSetor = respSetor;
    }

    public Integer getMinutosTotais() {
        return MinutosTotais;
    }

    public void setMinutosTotais(Integer MinutosTotais) {
        this.MinutosTotais = MinutosTotais;
    }

    public Integer getMinutosNegativos() {
        return MinutosNegativos;
    }

    public void setMinutosNegativos(Integer MinutosNegativos) {
        this.MinutosNegativos = MinutosNegativos;
    }

    public Long getRfid() {
        return rfid;
    }

    public void setRfid(Long rfid) {
        this.rfid = rfid;
    }

    public List<Hora> getHoras() {
        return horas;
    }

    public void setHoras(List<Hora> horas) {
        this.horas = horas;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.idFuncionario != null ? this.idFuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        if (this.idFuncionario != other.idFuncionario && (this.idFuncionario == null || !this.idFuncionario.equals(other.idFuncionario))) {
            return false;
        }
        return true;
    }

}
