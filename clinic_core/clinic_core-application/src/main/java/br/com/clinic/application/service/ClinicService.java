package br.com.clinic.application.service;


import java.io.Serializable;

public abstract class ClinicService<T, ID extends Serializable>  {

    public void beforeSave(T entity) { }

    public void afterSave(T entity) { }

    public void beforeUpdate(T entity) { }

    public void afterUpdate(T entity) { }



}
