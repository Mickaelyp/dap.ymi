package fr.houseofcode.dap.server.ymi.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

/**
 * @author ymi
 *
 */
@Entity
public class AppUser {

    @javax.persistence.Id
    @GeneratedValue
    private Integer Id;
    private String name;

    /**
     * @return the id
     */
    public Integer getId() {
        return Id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        Id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
