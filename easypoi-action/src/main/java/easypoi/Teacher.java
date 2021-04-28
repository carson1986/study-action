package easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;

@Data
@ExcelTarget("teacher")
public class Teacher implements Serializable {
    private static final long serialVersionUID = -3670215514588714158L;

    private Long id;

    @Excel(name = "主讲老师_major,代课老师_absent", needMerge = true)
    private String name;
}
