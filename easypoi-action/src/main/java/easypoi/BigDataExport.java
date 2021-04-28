package easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 大数据量导出
 */
public class BigDataExport {

    public List<User> generateUserList(int index){
        List<User> users = Lists.newArrayList();
        for(int i=1+index*10000; i<=10000+index*10000; i++){
            User user = new User();
            user.setId(new Long(i));
            user.setName("name"+i);
            user.setBirthday(new Date());
            users.add(user);
        }

        return users;
    }

    public void bigDataExport(String filePath) throws IOException {
        ExportParams exportParams = new ExportParams(null, "学生");
        exportParams.setType(ExcelType.XSSF); //设置xlsx格式，默认为xls格式
        Workbook workbook = null;
        for(int index = 1; index<=50; index++){
            List<User> userList = generateUserList(index);
            workbook = ExcelExportUtil.exportBigExcel(exportParams,
                    User.class, userList);
        }

        workbook.write(new FileOutputStream(filePath));
    }

    public static void main(String[] args) throws IOException {
        BigDataExport bigDataExport = new BigDataExport();
        String filePath = "D:/bigDataExpor.xlsx";
        bigDataExport.bigDataExport(filePath);
    }
}
