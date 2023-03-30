package com.powernode;

import com.powernode.mapper.UserMapper;
import com.powernode.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class PulsApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setName("张三");
        user.setAge(20);
        user.setEmail("zhangsan@xinlang.com");

        int rows = userMapper.insert(user);
        System.out.println("insert的果：" + rows);
    }

    @Test
    public void testInsertGetId(){
        User user = new User();
        user.setName("李四");
        user.setAge(20);
        user.setEmail("lisi@xinlang.com");

        int rows = userMapper.insert(user);
        System.out.println("insert user rows" + rows);

        int id = user.getId();
        System.out.println("主键id值：" + id);
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setName("修改数据");
        user.setAge(22);
        user.setEmail("edit@163.com");
        user.setId(2);
        int rows = userMapper.updateById(user);
        System.out.println("update rows" + rows);
    }

    @Test
    public void testDeleteId(){
        int rows = userMapper.deleteById(3);
        System.out.println("delete rows" + rows);
    }

    @Test
    public void testDeleteByMap(){
        Map<String, Object> map = new HashMap<>();
        //map.put("name", "张三");
        map.put("age", 0);
        int rows = userMapper.deleteByMap(map);
        System.out.println("delete rows" + rows);
    }

    @Test
    public void testDeleteBatchId(){
//        List<Integer> ids = new ArrayList<>();
//        ids.add(1);
//        ids.add(2);
//        ids.add(3);
//        ids.add(4);
//        ids.add(5);
        List<Integer> ids = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
        int rows = userMapper.deleteBatchIds(ids);
        System.out.println("delete rows" + rows);
    }

    @Test
    public void testSelectById(){
        User user = userMapper.selectById(6);
        System.out.println(user);
    }

    @Test
    public void testSelectByIds(){
        List<Integer> ids = new ArrayList<>();
        ids.add(6);
        ids.add(7);
        //SELECT id,name,email,age FROM t_user WHERE id IN ( ? , ? )
        List<User> list = userMapper.selectBatchIds(ids);
        for (User user:list){
            System.out.println(user);
        }
    }

    @Test
    public void testSelectByIds2(){
        List<Integer> ids = Stream.of(6, 7).collect(Collectors.toList());
        //SELECT id,name,email,age FROM t_user WHERE id IN ( ? , ? )
        List<User> list = userMapper.selectBatchIds(ids);
        for (User user:list){
            System.out.println(user);
        }
    }

    @Test
    public void testSelectMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 20);
        //SELECT id,name,email,age FROM t_user WHERE name = ? AND age = ?
        List<User> list = userMapper.selectByMap(map);
        for (User user:list){
            System.out.println(user);
        }
    }

}
