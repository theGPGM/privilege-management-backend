package org.george.pm.service;

import org.george.pm.mapper.PositionMapper;
import org.george.pm.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PositionService {
    @Autowired
    private PositionMapper positionMapper;

    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }

    public Integer addPosition(Position position) {
        position.setEnabled(true);
        position.setCreateDate(new Date());
        return positionMapper.insert(position);
    }

    public int deletePositionById(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public int deletePositionById(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    public int deletePositions(Integer[] ids) {
        return positionMapper.deleteByPrimaryKeys(ids);
    }
}
