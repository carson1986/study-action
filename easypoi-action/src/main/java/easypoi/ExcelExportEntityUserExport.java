package easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.google.common.collect.Maps;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelExportEntityUserExport {

    public List<Map<String, Object>> generateUserList(){
        List<Map<String, Object>> users = Lists.newArrayList();
        for(int i=1; i<=100; i++){
            Map<String, Object> user = Maps.newHashMap();
            user.put("id", Long.valueOf(i));
            user.put("name", "name"+i);
            user.put("birthday", new Date());
            users.add(user);
        }

        return users;
    }

    public void exportUser(List<Map<String, Object>> userList, String filePath) throws IOException {
        List<ExcelExportEntity> beanList = Lists.newArrayList();
        beanList.add(new ExcelExportEntity("编号", "id"));
        beanList.add(new ExcelExportEntity("姓名", "name"));
        beanList.add(new ExcelExportEntity("出生日期", "birthday"));

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("测试", "测试"), beanList ,
                userList);
        workbook.write(new FileOutputStream(filePath));
    }

    public static void main(String[] args) throws IOException {
        ExcelExportEntityUserExport userExport = new ExcelExportEntityUserExport();
        List<Map<String, Object>> userList = userExport.generateUserList();
        String filePath = "D:/excelExportEntityUserExport.xls";
        userExport.exportUser(userList, filePath);
    }
}
