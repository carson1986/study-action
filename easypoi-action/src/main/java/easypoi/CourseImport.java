package easypoi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;

import java.io.File;
import java.util.Date;
import java.util.List;

public class CourseImport {

    public static void main(String[] args) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(2);
        long start = new Date().getTime();
        List<Course> list = ExcelImportUtil.importExcel(
                new File("D:/exportCourse.xlsx"),
                Course.class, params);
        System.out.println(new Date().getTime() - start);
        System.out.println("数量: "+list.size());
        System.out.println("courseList: "+ JSON.toJSONString(list));
    }
}
