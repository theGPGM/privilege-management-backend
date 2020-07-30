package org.george.pm.service;

import org.george.pm.mapper.PoliticsStatusMapper;
import org.george.pm.model.PoliticsStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticsStatusService {
    @Autowired
    private PoliticsStatusMapper politicsStatusMapper;

    public List<PoliticsStatus> getAllPoliticsStatus() {
        return politicsStatusMapper.getAllPoliticsStatus();
    }
}
