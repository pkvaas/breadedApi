package com.breaded.breadedapi.service;

import java.util.List;

import com.breaded.breadedapi.entity.Myboxes;
import com.breaded.breadedapi.entity.User;

public interface MyBoxesService {

	Myboxes save(Myboxes myBoxes);
	
	List<Myboxes> findByUser(User user);
	
	List<Myboxes> findByUserAndPeriod(User user, String period);
}
