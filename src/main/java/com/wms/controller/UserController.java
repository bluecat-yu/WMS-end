package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.ResponseBean;
import com.wms.entity.User;
import com.wms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuhang
 * @since 2022-10-30
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    // 查询整个列表
    @GetMapping("/getList")
    public List<User> getlist() {
        return userService.list();
    }
    // 查询（模糊 匹配）
    @GetMapping("/getListByName")
    public ResponseBean getListByName(String name) {
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getName, name);
        return ResponseBean.success("成功", userService.list(lambdaQueryWrapper));
    }
    // 新增
    @PostMapping("/add")
    public ResponseBean add(@RequestBody User user) {
        return ResponseBean.success("成功", userService.save(user));
    }

    // 修改
    @PostMapping("/change")
    public ResponseBean change(@RequestBody User user) {
        return ResponseBean.success("成功", userService.updateById(user));
    }

    // 删除
    @GetMapping("/delete")
    public ResponseBean delete(Integer id) {
        return ResponseBean.success("成功", userService.removeById(id));
    }

    public ResponseBean listPage(@RequestBody QueryPageParam queryPageParam) {
        Page<User> page=new Page(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName, queryPageParam.getParam().get("name"));
        IPage result = userService.page(page,lambdaQueryWrapper);
        return ResponseBean.success("成功", result.getRecords());
    }
}
