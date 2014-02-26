package br.com.absoft.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "setor")
public class Setor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdSetor", nullable = false)
    private Integer idSetor;

    @Column(name = "Setor", nullable = false, length = 80)
    private String setor;

    //1 para muitos
    @OneToMany(mappedBy = "setor", fetch = FetchType.LAZY)
    @ForeignKey(name = "FuncionarioSetor")
    private List<Funcionario> funcionarios;

    //1 para muitos
    @OneToMany(mappedBy = "setor", fetch = FetchType.LAZY)
    @ForeignKey(name = "UsuarioSetor")
    private List<Usuario> usuarios;

    public Setor() {
    }

    public Integer getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Integer idSetor) {
        this.idSetor = idSetor;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.idSetor != null ? this.idSetor.hashCode() : 0);
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
        final Setor other = (Setor) obj;
        if (this.idSetor != other.idSetor && (this.idSetor == null || !this.idSetor.equals(other.idSetor))) {
            return false;
        }
        return true;
    }

}
