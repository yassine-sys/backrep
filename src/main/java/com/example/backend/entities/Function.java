package com.example.backend.entities;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name="function")
@Table(schema = "management")
@JsonIgnoreProperties("listreprapport")
public class Function implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String functionName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_module_id")
    @JsonBackReference
    private SubModule subModule;

    @ManyToMany(mappedBy = "liste_function",cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Group> group;


    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "function_reporting", joinColumns = @JoinColumn(name = "function_id") , inverseJoinColumns = @JoinColumn(name = "list_rep_id") , schema = "management")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RepRapport> listreprapport = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public List<Group> getGroup() {
        return group;
    }

    public void setGroup(List<Group> group) {
        this.group = group;
    }

    public SubModule getSubModule() {
        return subModule;
    }

    public void setSubModule(SubModule subModule) {
        this.subModule = subModule;
    }

    public List<RepRapport> getListreprapport() {
        return listreprapport;
    }

    public void setListreprapport(List<RepRapport> listreprapport) {
        this.listreprapport = listreprapport;
    }
}
