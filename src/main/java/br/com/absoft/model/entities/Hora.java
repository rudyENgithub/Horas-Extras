package br.com.absoft.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "hora")
public class Hora implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdHora")
    private Integer idHora;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DataRegistro", nullable = false)
    private Date dataRegistro;

    @Column(name = "Motivo", nullable = false, length = 500)
    private String motivo;

    @Column(name = "TempoMinutos")
    private Integer tempoMinutos;

    @Column(name = "Tipo", nullable = false)
    private char tipo;
    
    @Column(name = "Usuario", nullable = false, length = 20)
    private String usuario;

    //Muitos para 1
    @ManyToOne(optional = false)
    @ForeignKey(name = "HoraFuncionario")
    @JoinColumn(name = "IdFuncionario", referencedColumnName = "IdFuncionario")
    private Funcionario funcionario;

    public Hora() {
        funcionario = new Funcionario();
    }

    public Integer getIdHora() {
        return idHora;
    }

    public void setIdHora(Integer idHora) {
        this.idHora = idHora;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getTempoMinutos() {
        return tempoMinutos;
    }

    public void setTempoMinutos(Integer tempoMinutos) {
        this.tempoMinutos = tempoMinutos;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (this.idHora != null ? this.idHora.hashCode() : 0);
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
        final Hora other = (Hora) obj;
        if (this.idHora != other.idHora && (this.idHora == null || !this.idHora.equals(other.idHora))) {
            return false;
        }
        return true;
    }

}
