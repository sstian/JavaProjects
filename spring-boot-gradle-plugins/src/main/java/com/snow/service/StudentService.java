package com.snow.service;

import com.snow.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Student)表服务接口
 *
 * @author makejava
 * @since 2021-12-05 20:24:40
 */
public interface StudentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Student queryById(Integer id);

    /**
     * 分页查询
     *
     * @param student     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Student> queryByPage(Student student, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    Student insert(Student student);

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    Student update(Student student);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
