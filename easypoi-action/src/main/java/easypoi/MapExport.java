package easypoi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * map类型导入
 */
public class MapExport {

    public static void main(String[] args) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
//        params.setDataHanlder(new MapImportHanlder());
        long start = new Date().getTime();
        List<Map<String, Object>> list = ExcelImportUtil.importExcel(
                new File("D:/excelExportEntityUserExport.xls"), Map.class, params);

        System.out.println(JSON.toJSONString(list));
    }
}
