package easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CourseExport {

    public List<Course> generateCourseList(){
        List<Course> courseList = Lists.newArrayList();
        for(int i=1; i<=5; i++){
            Course course = new Course();
            course.setId(new Long(i));
            course.setName("课程"+i);

            Teacher teacher = new Teacher();
            course.setTeacher(teacher);
            teacher.setId(new Long(i));
            teacher.setName("老师"+i);

            List<User> users = Lists.newArrayList();
            course.setUsers(users);
            for(int j=1; j<=3; j++){
                User user = new User();
                user.setId(new Long(i));
                user.setName("name"+i);
                user.setBirthday(new Date());
                users.add(user);
            }

            courseList.add(course);
        }

        return courseList;
    }

    public void exportCourse(List<Course> courseList, String filePath) throws IOException {
        ExportParams exportParams = new ExportParams("课程", "课程");
        exportParams.setType(ExcelType.XSSF); //设置xlsx格式，默认为xls格式
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,
                Course.class, courseList);
        workbook.write(new FileOutputStream(filePath));
    }

    public static void main(String[] args) throws IOException {
        CourseExport courseExport = new CourseExport();
        List<Course> courseList = courseExport.generateCourseList();
        String filePath = "D:/exportCourse.xlsx";
        courseExport.exportCourse(courseList, filePath);
    }
}
