/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface IRepository<T>{
    public List<T> SelectAll();

    public T save(T entity);

    public T findById(String ma);
    
    public List<T> findList(String ma);

    public String delete(String ma);
}
