package br.com.watlas.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ICRUD_GENERIC<T> {

   

    public void Add(T objeto) throws Exception;

    public void Delete(int n)throws Exception;

    public void Update(T objeto)throws Exception;

    public List getAll()throws Exception;

    public Object getById(int n)throws Exception;

    public Object getByNome(String nome)throws Exception;



}
