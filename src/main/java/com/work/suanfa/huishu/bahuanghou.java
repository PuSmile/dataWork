package com.work.suanfa.huishu;

import java.util.*;

/**
 * 在n×n格的国际象棋上摆放8个皇后，使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法
 */
public class bahuanghou {
    public static void main(String[] args) {
        //n皇后
        int n=8;
        List<List<String>> queen = queen(n);
        queen.forEach(list->{
            System.out.println("---------");
            list.forEach(s->{
                System.out.println(s);
            });
            System.out.println("---------");
        });


    }
    private static  List<List<String>>   queen(int n){
        //总共的可能
        List<List<String>>  all=new ArrayList<>();
        //每行摆放的位置
        int[] queen=new int[n];
        Arrays.fill(queen,-1);//初始化为-1
        Set<Integer> coulmn=new HashSet<Integer>();//列标记
        Set<Integer> diagonal1=new HashSet<Integer>();//斜线右上到左下
        Set<Integer> diagonal2=new HashSet<Integer>();//斜线左上到右下
        //递归
        digui(queen,all,coulmn,diagonal1,diagonal2,0,n);
        return all;
    }

    private static void digui(int[] queen, List<List<String>> all, Set<Integer> coulmn, Set<Integer> diagonal1, Set<Integer> diagonal2, int row, int n) {
        //递归常用
        if (row==n){
            //该轮递归结束
            List<String> strings = covertList(queen,n);
            all.add(strings);
        }else {
            for (int i = 0; i < n; i++) {
                if (coulmn.contains(i)){
                    continue;//此循环不符合跳出该次循环
                }
                //右上到左下  该斜线上相加和相同
                int diagonalRight=row+i;
                if (diagonal1.contains(diagonalRight)){
                    continue;
                }
                //左上到右下  改斜线上相减相同
                int diagonlLeft=row-i;
                if (diagonal2.contains(diagonlLeft)){
                    continue;
                }
                //上面通过则该位置可以放置皇后
                coulmn.add(i);
                diagonal1.add(diagonalRight);
                diagonal2.add(diagonlLeft);
                queen[row]=i;
                digui(queen,all,coulmn,diagonal1,diagonal2,row+1,n);
                //全跑完行之后  列还需继续跑 所以不能往下继续 要先清空该列存在的
                coulmn.remove(i);
                diagonal1.remove(diagonalRight);
                diagonal2.remove(diagonlLeft);
                queen[row]=-1;
            }



        }
    }


    //数组转化成list
    private static List<String> covertList(int[] queen,int n) {
        List<String> returnList=new ArrayList<>();
        for (int i = 0; i < queen.length; i++) {
            char[]  data=new char[n];
            Arrays.fill(data,'*');
            data[queen[i]]='Q';
            //这里说明char数组就是string
            returnList.add(new String(data));
        }
        return returnList;
    }

}
