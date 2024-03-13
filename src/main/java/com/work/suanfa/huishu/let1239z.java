package com.work.suanfa.huishu;

import jdk.jfr.StackTrace;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如
 * 果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
 * 请返回所有可行解 s 中最长长度。
 */
public class let1239z {
    public static void main(String[] args) {
//        String content="--Select \n" +
//                "--dau,payment,rate,arpu,arppu,dau1,payment1,rate1,arpu1,arppu1, \n" +
//                "--from pri_oversea.pay_log where game_id=999 and\n" +
//                "select 1" +
//                "--s \n unoin select 2";
//        String s = Pattern.compile("--.*?$", Pattern.MULTILINE).matcher(content).replaceAll("");
//        System.out.println(content);
//        System.out.println("---");
//        System.out.println(s);
//        maxLength(new ArrayList<>());
    }



    //使用位图的思路
    public static int maxLength(List<String> arr) {
        arr.add("sd");
        arr.add("ax");
        //存放每一个字符串的位图数组
        int[] bitMap = new int[arr.size()];
        //存放每一个字符串的长度
        int[] lens = new int[arr.size()];
        int idx = 0;
        //遍历字符串数组，将每一个字符串转成位图形式
        for (String s : arr) {
            bitMap[idx] = getBitMask(s);
            lens[idx] = s.length();
            idx++;
        }

        return dfs(bitMap, lens, 0, 0);
    }

    /*
    Depth First Search 深度优先搜索
    返回串联字符的最大长度(从start开始，尝试拼接上一步的num)，拼接字符串转换成了按位或运算
     */
    public static int dfs(int[] bitMap, int[] lens, int start, int num) {
        if (start >= bitMap.length) //递归结束条件
            return 0;

        int ans = 0;
        for (int i = start; i < bitMap.length; i++) {
            //1.当前子字符位掩码不为-1（为-1的话证明子字符里面有重复字符）
            //2.当前子字符位掩码与前面字符的位掩码与运算结果为0（如果结果不为0，那么说明与之前的字符串有重复字符）
            //满足上述两个条件才进入一下层递归。
            if (bitMap[i] != -1 && (bitMap[i] & num) == 0) {
                //计算当前的最大长度
                ans = Math.max(ans, dfs(bitMap, lens, i + 1, num | bitMap[i]) + lens[i]);
            }
        }
        return ans;
    }

    /*
    校验相同数字
     */
    /*
    将字符串转成数字，（类似于布隆表达式）
    一个26位的二进制数，最高为表示是否包含字符z，最低位表示是否包含字符a
     */
    public static int getBitMask(String s) {
        int bitMask = 0;
        for (char c : s.toCharArray()) {
            int bit = 1 << (c - 'a'); //生成该字符对应的 二进制标识
            if ((bitMask & bit) != 0) //存在重复字符，返回-1
                return -1;
            bitMask |= bit;
        }
        return bitMask;
    }

}
