package com.powernode;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.powernode.mapper.DeptMapper;
import com.powernode.pojo.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptARTest {
    @Test
    public void testARInsert(){
        Dept dept = new Dept();
        dept.setName("研发部");
        dept.setMobile("011-12345678");
        dept.setManager(3);

        boolean flag = dept.insert();
        System.out.println("insert result:" + flag);
    }

    @Test
    public void testARUpdate(){
        Dept dept = new Dept();
        dept.setId(1);
        dept.setMobile("101-12365487");
        dept.setManager(2);
        //UPDATE dept SET mobile=?, manager=? WHERE id=?
        boolean flag = dept.updateById();
        System.out.println("insert result:" + flag);
    }

    @Test
    public void testARDeleteById(){
        Dept dept = new Dept();
        boolean flag = dept.deleteById(1);
        System.out.println("delete result:" + flag);
    }

    @Test
    public void testARDeleteByIds(){
        Dept dept = new Dept();
        dept.setId(2);
        boolean flag = dept.deleteById();
        System.out.println("delete result:" + flag);
    }

    @Test
    public void testARSelectById(){
        Dept dept = new Dept();
        //SELECT id,name,mobile,manager FROM dept WHERE id=?
        Dept dept1 = (Dept) dept.selectById(1);
        System.out.println(dept1);
    }

    @Resource
    DeptMapper deptMapper;
    @Test
    public void testAllEq(){
        QueryWrapper<Dept> qw = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("t_name", "研发部");
        map.put("manager", 3);
        qw.allEq(map);
        //SELECT id,t_name AS name,mobile,manager FROM dept WHERE manager = ? AND t_name = ?
        List<Dept> list = deptMapper.selectList(qw);
        for (Dept dept:list){
            System.out.println(dept);
        }
    }

    @Test
    public void testAllEq2(){
        QueryWrapper<Dept> qw = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("t_name", "研发部");
        map.put("manager", null);
        qw.allEq(map, true);
        //SELECT id,t_name AS name,mobile,manager FROM dept WHERE manager IS NULL AND t_name = ?
        List<Dept> list = deptMapper.selectList(qw);
        for (Dept dept:list){
            System.out.println(dept);
        }
    }

    @Test
    public void testBetween(){
        QueryWrapper<Dept> qw = new QueryWrapper<>();
        //qw.between("manager", 1, 3);
        qw.notBetween("manager", 1, 3);
        List<Dept> list = deptMapper.selectList(qw);
        for (Dept dept:list){
            System.out.println(dept);
        }
    }

    @Test
    public void testLike(){
        QueryWrapper<Dept> qw = new QueryWrapper<>();
        //qw.like("mobile", 8);
        //SELECT id,t_name AS name,mobile,manager FROM dept WHERE mobile LIKE ?
        qw.notLike("mobile", 8);
        //WHERE mobile NOT LIKE ?
        List<Dept> list = deptMapper.selectList(qw);
        for (Dept dept:list){
            System.out.println(dept);
        }
    }

    @Test
    public void testPage(){
        QueryWrapper<Dept> qw = new QueryWrapper<>();
        qw.gt("manager", 2);
        IPage<Dept> page = new Page<>();
        page.setCurrent(1);//第一页
        page.setSize(3);//每页的记录数

        IPage<Dept> result = deptMapper.selectPage(page, qw);
        //分页后的记录
        List<Dept> depts = result.getRecords();
        System.out.println(depts.size());
        //分页后的信息
        long pages = result.getPages();
        System.out.println("页数：" + pages);
        System.out.println("总记录数：" + result.getTotal());
        System.out.println("当前页码：" + result.getCurrent());
        System.out.println("每页的记录数：" + result.getSize());
    }

}
