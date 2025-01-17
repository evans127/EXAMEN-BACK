package com.mx.Moneda.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.Moneda.Dao.IMonedasDao;
import com.mx.Moneda.Dominio.Moneda;

@Service
public class MonedasService {

	@Autowired
	public IMonedasDao dao;
	
	public List<Moneda>listar(){
		return dao.findAll(Sort.by(Sort.Direction.ASC,"numCia"));
		
	}
	public Moneda guardar(Moneda moneda) {
		return dao.save(moneda);
				}
	
	public Moneda buscar(int numCia) {
		return dao.findById(numCia).orElse(null);
	}
	public void eliminar(int numCia) {
		dao.deleteById(numCia);
	}
	
	public List<Moneda>byStatus(String status){
		return dao.findByStatusIgnoringCaseContaining(status);
	}
	}
