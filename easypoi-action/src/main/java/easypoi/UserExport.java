package easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 导出xls
 */
public class UserExport {

    public List<User> generateUserList(){
        List<User> users = Lists.newArrayList();
        for(int i=1; i<=100; i++){
            User user = new User();
            user.setId(new Long(i));
            user.setName("name"+i);
            user.setBirthday(new Date());
            users.add(user);
        }

        return users;
    }

    public void exportUser(List<User> userList, String filePath) throws IOException {
        ExportParams exportParams = new ExportParams(null, "学生");
//        exportParams.setType(ExcelType.XSSF); //设置xlsx格式，默认为xls格式
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,
                User.class, userList);
        workbook.write(new FileOutputStream(filePath));
    }

    public static void main(String[] args) throws IOException {
        UserExport userExport = new UserExport();
        List<User> userList = userExport.generateUserList();
        String filePath = "D:/exportUser.xls";
        userExport.exportUser(userList, filePath);
    }
}
