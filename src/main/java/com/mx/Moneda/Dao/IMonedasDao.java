package com.mx.Moneda.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Moneda.Dominio.Moneda;

public interface IMonedasDao  extends JpaRepository<Moneda, Integer>{
public List<Moneda>findByStatusIgnoringCaseContaining(String status);




}
