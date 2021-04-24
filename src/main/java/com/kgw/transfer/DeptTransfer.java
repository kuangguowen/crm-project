package com.kgw.transfer;

import com.kgw.domin.entity.Brand;
import com.kgw.domin.entity.Dept;
import com.kgw.domin.vo.BrandVo;
import com.kgw.domin.vo.DeptVo;
import com.kgw.transfer.base.BaseTransfer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/4/17 17:12
 */
@Component
public class DeptTransfer extends BaseTransfer<Dept, DeptVo> {


    /**
     *
     */
    public List<DeptVo> addChildrenProperties(List<Dept> list){
        List<DeptVo> deptVos = super.toVO(list);
        return deptVos;
    }
}
