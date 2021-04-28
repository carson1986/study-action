package easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
//ExcelTarget是作用于最外层的对象,描述这个对象的id,以便支持一个对象可以针对不同导出做出不同处理
@ExcelTarget("user")
public class User implements Serializable {
    private static final long serialVersionUID = -8889297304567999108L;

    @Excel(name="学号")
    private Long id;

    @Excel(name="姓名")
    private String name;

    @Excel(name="出生日期", format = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
}
