package hu.schonherz.training.landing.service.vo;

import java.io.Serializable;

/**
 * Created on 2016.08.26..
 */

public class RoleVo implements Serializable {

    private static final long serialVersionUID = -8403753397412273249L;

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}