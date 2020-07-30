package org.george.pm.controller.system.basic;

import org.george.pm.model.Position;
import org.george.pm.model.RespBean;
import org.george.pm.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @GetMapping("/")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        if(positionService.addPosition(position) == 1)
            return RespBean.ok("添加成功");
        else
            return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        if(positionService.deletePositionById(position) == 1)
            return RespBean.ok("更新成功");
        else
            return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        if(positionService.deletePositionById(id) == 1)
            return RespBean.ok("删除成功");
        else
            return RespBean.error("删除失败!");
    }

    @DeleteMapping("/")
    public RespBean deletePositions(Integer[] ids){
        if(positionService.deletePositions(ids) == ids.length){
            return RespBean.ok("批量删除成功");
        }else{
            return RespBean.error("删除失败!");
        }
    }
}
