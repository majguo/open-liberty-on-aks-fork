package cafe.web.view;

import java.io.IOException;
import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@SessionScoped
public class Cafe implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameFromSession() {
        Object obj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("name");
        return obj == null ? "" : obj.toString();
    }

    public void submit() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("name", name);
        FacesContext.getCurrentInstance().getExternalContext().redirect("");
    }
}
