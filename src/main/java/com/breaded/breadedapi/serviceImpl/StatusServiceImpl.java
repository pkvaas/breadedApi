package com.breaded.breadedapi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breaded.breadedapi.entity.Status;
import com.breaded.breadedapi.repository.StatusRepository;
import com.breaded.breadedapi.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	StatusRepository statusRepository;
	
	@Override
	public Status save(Status status) {
		return statusRepository.save(status);
	}

	@Override
	public List<Status> findAll() {
		return (List<Status>) statusRepository.findAll();
	}

}
