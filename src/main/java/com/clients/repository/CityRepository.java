package com.clients.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clients.enumeration.StateEnum;
import com.clients.model.City;

public interface CityRepository extends JpaRepository<City, Integer>{

	@Query(value = "SELECT c FROM City c "
            + "WHERE c.name like CONCAT('%',:name,'%') "
            + "ORDER by c.name")
	List<City> findByName(@Param(value = "name") String name);

	List<City> findByStateOrderByName(StateEnum state);

}
