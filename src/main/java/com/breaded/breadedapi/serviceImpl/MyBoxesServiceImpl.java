package com.breaded.breadedapi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breaded.breadedapi.entity.Myboxes;
import com.breaded.breadedapi.entity.User;
import com.breaded.breadedapi.repository.MyBoxesRepository;
import com.breaded.breadedapi.service.MyBoxesService;

@Service
public class MyBoxesServiceImpl implements MyBoxesService {

	@Autowired
	MyBoxesRepository myBoxesRepository;
	
	@Override
	public Myboxes save(Myboxes myBoxes) {
		return myBoxesRepository.save(myBoxes);
	}

	@Override
	public List<Myboxes> findByUser(User user) {
		return myBoxesRepository.findByUser(user);
	}

}
