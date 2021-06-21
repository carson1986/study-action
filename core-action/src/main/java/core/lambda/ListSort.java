package core.lambda;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSort {

    public static List<Student> buildStudentList(){
        List<Student> students = Lists.newArrayList();

        students.add(new Student("C", 50));
        students.add(new Student("A", 10));
        students.add(new Student("B", 100));

        return students;
    }

    /**
     * 不使用lambda的排序
     */
    public static void sort(){

        List<Student> beforeList = buildStudentList();
        System.out.println("构建原始的列表，beforeList=" + JSON.toJSONString(beforeList));

        Comparator<Student> comparatorWithName = new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        beforeList.sort(comparatorWithName);

        System.out.println("按姓名排序后的列表，beforeList=" + JSON.toJSONString(beforeList));

        Comparator<Student> comparatorWithAge = new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        };
        Collections.sort(beforeList, comparatorWithAge);
        System.out.println("按年龄排序后的列表，beforeList=" + JSON.toJSONString(beforeList));
    }

    public static void sortWithLambda(){

        List<Student> beforeList = buildStudentList();
        System.out.println("构建原始的列表，beforeList=" + JSON.toJSONString(beforeList));


        beforeList.sort((o1, o2)->o1.getName().compareTo(o2.getName()));

        System.out.println("按姓名排序后的列表，beforeList=" + JSON.toJSONString(beforeList));

        beforeList.sort(Comparator.comparing(Student::getAge));
        System.out.println("按年龄排序后的列表，beforeList=" + JSON.toJSONString(beforeList));
    }

    public static void main(String[] args) {
//        sort();

        sortWithLambda();
    }
}

class Student{
    private String name;
    private Integer age;

    public Student(){
        super();
    }

    public Student(String name, Integer age){
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
