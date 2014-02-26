package br.com.absoft.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "IdUsuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "Usuario", nullable = false, length = 20, unique = true)
    private String usuario;

    @Column(name = "Senha", nullable = false, length = 45)
    private String senha;

    @Column(name = "Nome", nullable = false, length = 80)
    private String nome;

    @Column(name = "Permissao", nullable = false, length = 25)
    private String permissao;

    @Column(name = "RespSetor", nullable = false)
    private char respSetor;

    //Muitos para 1
    @ManyToOne(optional = false)
    @ForeignKey(name = "UsuarioSetor")
    @JoinColumn(name = "IdSetor", referencedColumnName = "IdSetor")
    private Setor setor;

    public Usuario() {
        setor = new Setor();
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public char getRespSetor() {
        return respSetor;
    }

    public void setRespSetor(char respSetor) {
        this.respSetor = respSetor;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.idUsuario != null ? this.idUsuario.hashCode() : 0);
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
        final Usuario other = (Usuario) obj;
        if (this.idUsuario != other.idUsuario && (this.idUsuario == null || !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

}
