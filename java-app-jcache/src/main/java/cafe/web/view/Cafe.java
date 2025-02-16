package cafe.web.view;

import java.io.IOException;
//import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import cafe.model.entity.Coffee;

@Named
@SessionScoped
public class Cafe implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient String baseUri;
    private transient Client client;

    @NotNull
    @NotEmpty
    protected String name;
    @NotNull
    protected Double price;

    protected transient List<Coffee> coffeeList;

    public String getName() {
        System.out.println("Getting name " + name);
        return name;
    }

    public void setName(String name) {
        System.out.println("Setting name to " + name);
        this.name = name;
    }

    public Double getPrice() {
        System.out.println("Getting price " + price);
        return price;
    }

    public void setPrice(Double price) {
        System.out.println("Setting price to " + price);
        this.price = price;
    }

    public List<Coffee> getCoffeeList() {
        this.getAllCoffees();
        return coffeeList;
    }

    public String getHostName() {
        return System.getenv("HOSTNAME");
    }

    @PostConstruct
    private void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();

        baseUri = "http://localhost:9080" + request.getContextPath() + "/rest/coffees";
        this.client = ClientBuilder.newBuilder().build();
    }

    private void getAllCoffees() {
        this.coffeeList = this.client.target(this.baseUri).path("/").request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Coffee>>() {
                });
    }

    public void addCoffee() throws IOException {
        Coffee coffee = new Coffee(this.name, this.price);
        this.client.target(baseUri).request(MediaType.APPLICATION_JSON).post(Entity.json(coffee));
        FacesContext.getCurrentInstance().getExternalContext().redirect("");
    }

    public void removeCoffee(String coffeeId) throws IOException {
        this.client.target(baseUri).path(coffeeId).request().delete();
        FacesContext.getCurrentInstance().getExternalContext().redirect("");
    }

    // private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
    //     // read object and log the objects
    //     ois.defaultReadObject();
    //     System.out.println("Deserialized object: " + this);
    //     init();
    // }

    private Object readResolve() {
        init();
        return this;
    }
}
