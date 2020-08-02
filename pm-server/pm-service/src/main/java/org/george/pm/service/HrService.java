package org.george.pm.service;

import org.george.pm.mapper.HrMapper;
import org.george.pm.mapper.HrRoleMapper;
import org.george.pm.model.Hr;
import org.george.pm.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HrService implements UserDetailsService {
    @Autowired
    private HrMapper hrMapper;

    @Autowired
    private HrRoleMapper hrRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Hr hr = hrMapper.loadUserByUsername(username);
        if(hr == null)
            throw new UsernameNotFoundException("用户名不存在");
        hr.setRoles(hrMapper.getHrRolesById(hr.getId()));
        return hr;
    }

    public List<Hr> getAllHr(String keywords) {
        return hrMapper.getAllHr(HrUtils.getCurrentHr().getId(), keywords);
    }

    public Integer updateHr(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    /**
     * 更新用户角色
     * @param id
     * @param rids
     * @return
     */
    @Transactional
    public boolean updateRoles(Integer id, Integer[] rids) {
        hrRoleMapper.deleteHrRolesById(id);
        //处理最后一个 rid 为空的情况
        if(rids.length == 0) return true;
        List<Integer> list = new ArrayList<>();
        for(Integer rid : rids){
            if(rid != null) list.add(rid);
        }
        rids = list.toArray(new Integer[list.size()]);
        return hrRoleMapper.insertHrRoles(id, rids) == rids.length;
    }

    public Integer deleteHrByHrId(Integer id) {
        return hrMapper.deleteByPrimaryKey(id);
    }

    public List<Hr> getAllHrWithoutCurrentHr() {
        return hrMapper.getAllHrWithoutCurrentHr(HrUtils.getCurrentHr().getId());
    }

    public boolean updateHrPassword(Integer id, String oldPass, String newPass) {
        Hr hr = hrMapper.selectByPrimaryKey(id);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(oldPass, hr.getPassword())){
            String encodePass = encoder.encode(newPass);
            Integer result = hrMapper.updatePassword(id, encodePass);
            if(result == 1)
                return true;
        }
        return false;
    }
}
