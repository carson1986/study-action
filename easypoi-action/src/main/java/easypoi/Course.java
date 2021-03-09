package easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ExcelTarget("course")
public class Course implements Serializable {
    private static final long serialVersionUID = 3483535530665446582L;

    private Long id;

    @Excel(name="课程", needMerge = true)
    private String name;

    @ExcelEntity(id = "major")
    private Teacher teacher;

    @ExcelCollection(name="学生", orderNum = "3")
    private List<User> users;
}
