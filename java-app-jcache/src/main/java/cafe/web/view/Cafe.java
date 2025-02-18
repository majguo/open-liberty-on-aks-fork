package cafe.web.view;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class Cafe implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public String getName() {
        System.out.println("getName() called: " + name);
        return name;
    }

    public void setName(String name) {
        System.out.println("setName() called: " + name);
        this.name = name;
    }
}
