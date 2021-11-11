package easypoi;

import com.google.common.base.Joiner;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.RandomUtils;

import java.util.Collections;
import java.util.List;

/**
 * 每日数学
 */
public class MathDaily {

    /**
     * 生成30道加法题
     * @param totalMax
     * @return
     */
    public static List<String> generateAdd(int totalMax){
        List<String> returnList = Lists.newArrayList();
        int num = 0;
        while(num<=30){
            int first = getNum(totalMax);
            int second = 0;
            do{
                second = getNum(totalMax);
            }while(first + second > totalMax);

            String item = Joiner.on("").join(first, " + ", second, " = ");
            if(!returnList.contains(item)) {
                returnList.add(item);
                num++;
            }
        }

        return returnList;
    }

    /**
     * 生成30道减法题
     * @param max
     * @return
     */
    public static List<String> generateSubtract(int max){
        List<String> returnList = Lists.newArrayList();
        int num = 0;
        while(num<=30){
            int first = getNum(max);
            int second = 0;
            do{
                second = getNum(max);
            }while(first < second);

            String item = Joiner.on("").join(first, " - ", second, " = ");
            if(!returnList.contains(item)) {
                returnList.add(item);
                num++;
            }
        }

        return returnList;
    }

    /**
     * 生成算法题目
     * @param max 算术中出现的最大值（不包含）
     * @param num 题目数量
     * @return
     */
    public static List<String> generate(int max, int num){
        List<String> returnList = Lists.newArrayList();
        returnList.addAll(generateAdd(max));
        returnList.addAll(generateSubtract(max));

        Collections.shuffle(returnList);

        return returnList.subList(0, num);
    }

    private static int getNum(int totalMax){
        return RandomUtils.nextInt(1, totalMax);
    }

    public static void main(String[] args) {
        List<String> list = generate(10, 30);

        for(String item : list){
            System.out.println(item);
        }
    }
}
