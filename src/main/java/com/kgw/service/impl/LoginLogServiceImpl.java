package com.kgw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgw.commom.page.PageResult;
import com.kgw.domin.entity.LoginLog;
import com.kgw.domin.query.LoginLogCriteria;
import com.kgw.domin.vo.LoginLogVo;
import com.kgw.mapper.LoginLogMapper;
import com.kgw.service.LoginLogService;
import com.kgw.service.base.impl.BaseServiceImpl;
import com.kgw.transfer.LoginLogTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/15 19:51
 */
@Service  //service层添加到容器
@Transactional  // 添加事务
@RequiredArgsConstructor
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLog> implements LoginLogService {

    private final LoginLogMapper loginLogMapper;

    private final LoginLogTransfer loginLogTransfer;

    /**
     * 分页条件查询
     */
    @Override
    public PageResult<LoginLogVo> searchPage(LoginLogCriteria loginLogCriteria) {
        // 开启分页
        PageHelper.startPage(loginLogCriteria.getCurrentPage(), loginLogCriteria.getPageSize());
        // 查询
        LambdaQueryWrapper<LoginLog> lambda = new QueryWrapper<LoginLog>().lambda();
        if (!StringUtils.isEmpty(loginLogCriteria.getAdminName())) {
            lambda.like(LoginLog::getAdminName, loginLogCriteria.getAdminName());
        }
        // 拿到时间 进行比较
        LocalDateTime startTime = loginLogCriteria.getStartTime();
        LocalDateTime endTime = loginLogCriteria.getEndTime();
        if (startTime != null && endTime != null) {
            // 判断时间 在什么 与 什么之间
            lambda.between(LoginLog::getLoginTime, startTime, endTime);
        }
        List<LoginLog> loginLogs = loginLogMapper.selectList(lambda);

        // 获取总条数
        PageInfo<LoginLog> pageInfo = new PageInfo<>(loginLogs);
        System.out.println(pageInfo.getTotal());
        return new PageResult<LoginLogVo>(pageInfo.getTotal(), loginLogTransfer.toVO(loginLogs));
    }

    /**
     * VO 专门用来与前端交互的方法
     * @param id
     * @return
     */

    /**
     * 通过id进行查询
     */
    @Override
    public LoginLogVo findById(Long id) {
        LoginLog byId = getById(id);
        return loginLogTransfer.toVo(byId);
    }
}
