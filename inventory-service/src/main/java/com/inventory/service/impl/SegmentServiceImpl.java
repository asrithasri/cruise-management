package com.inventory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.entity.Cruise;
import com.inventory.entity.Segment;
import com.inventory.repositoty.SegmentRepository;
import com.inventory.service.SegmentService;

import common.exception.NotFoundException;

@Service
public abstract class SegmentServiceImpl implements SegmentService{
	
	@Autowired
	private SegmentRepository segmentRepository;
	
	
	public SegmentServiceImpl(SegmentRepository segmentRepository) {
		super();
		this.segmentRepository = segmentRepository;
	}

	@Override
	public List<Segment> findAllSegment() {
		return segmentRepository.findAll();
	}

	@Override
	public Segment findSegmentById(Long segmentId) {
		return segmentRepository.findById(segmentId)
				.orElseThrow(() ->new NotFoundException(String.format("Segment not found with ID %d",segmentId)));
	
	}

	@Override
	public Segment createSegment(Segment segment) {
		return segmentRepository.save(segment);
		
	}

	@Override
	public Segment updateSegment(Segment segment) {
		Segment existingSegment = segmentRepository.findById(segment.getSegmentId()).get();
		existingSegment.setDay(segment.getDay());
		existingSegment.setDay(segment.getDay());		
		return segmentRepository.save(existingSegment);
		
		
	}
		

	@Override
	public void deleteSegment(Long segmentId) {
		final Segment segment = segmentRepository.findById(segmentId)
				.orElseThrow(() -> new NotFoundException(String.format("Segment not found with ID %d",segmentId)));
		segmentRepository.deleteById(segment.getSegmentId());
		
		
	}

}
