
package br.com.absoft.suport;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "msgs")
@RequestScoped
public class Msgs implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    public void erroLogin(){
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha incorretos!", ""));
        
    }
    
}
