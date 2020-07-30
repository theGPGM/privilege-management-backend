package org.george.pm;

import org.george.pm.mapper.CurrentWorkSequenceMapper;
import org.george.pm.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VhrApplicationTests {
    @Autowired
    MenuService menuService;

    @Autowired
    CurrentWorkSequenceMapper currentWorkSequenceMapper;
    @Test
    void contextLoads() {
                Integer workId = currentWorkSequenceMapper.getCurrentMaxWorkId();
                currentWorkSequenceMapper.incWorkId(String.format("%08d", workId + 1));
    }

}
