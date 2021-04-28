package easypoi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 导入xls，转化为对象
 */
public class UserImport {

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        long start = new Date().getTime();
        List<User> list = ExcelImportUtil.importExcel(
                new File("D:/exportUser.xls"),
                User.class, params);
        System.out.println("cost time: "+ stopwatch.elapsed());
        System.out.println(list.size());
        System.out.println(JSON.toJSONString(list));
    }
}
