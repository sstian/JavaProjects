package com.snow.service.impl;

import com.snow.entity.Student;
import com.snow.dao.StudentDao;
import com.snow.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * (Student)表服务实现类
 *
 * @author makejava
 * @since 2021-12-05 20:24:42
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
//    @Resource
//    private StudentDao studentDao;

    private final Map<Integer, Student> students = new HashMap<>();

    public StudentServiceImpl() {
        this.students.put(1, new Student(1, "Tom", 20));
        this.students.put(2, new Student(2, "Jerry", 30));
        this.students.put(3, new Student(3, "Lucy", 18));
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Student queryById(Integer id) {
        return this.students.get(id);
    }

    /**
     * 分页查询
     *
     * @param student     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Student> queryByPage(Student student, PageRequest pageRequest) {
        long total = this.students.size();
//        return new PageImpl<>(this.studentDao.queryAllByLimit(student, pageRequest), pageRequest, total);
        return null;
    }

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    @Override
    public Student insert(Student student) {
        int id = this.students.size() + 1;
        this.students.put(id, student);
        return student;
    }

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    @Override
    public Student update(Student student) {
       return this.students.put(student.getId(), student);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.students.remove(id) != null;
    }
}
