/**
 * 
 */
package com.cc.oms.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cc.oms.entities.CustOrder;


public interface OrderRepository extends CrudRepository<CustOrder, Integer> {
	List<CustOrder> findByUserId(int userId);

}
