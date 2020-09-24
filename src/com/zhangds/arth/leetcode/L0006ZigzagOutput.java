package com.zhangds.arth.leetcode;

/**
 * 之字形输出一个字符串，返回按行读取的字符串
 *
 * abcdefghij  3
 *
 * a   e   i            0 4 8 12        4
 * b d f h j            1 3 5 7         2
 * c   g
 *
 * a     g     m        0 6 12 18       6 2(n-1)        第二行     2
 * b   f h   l          1 5 7 11        4 2n            第三行     4
 * c e   i k                                            第四行     6
 * d     j
 *
 * a   i
 * b  hj
 * c g
 * df
 * e
 * 1 7 9 14
 */
public class L0006ZigzagOutput {

    public static void main(String[] args) {
        System.out.println(convert("abcdefghijklm", 4));
        System.out.println(convert2("abcdefghijklm", 4));
    }

    /**
     * 两个峰顶/峰谷的周期是 2*(numRows-1)
     * 按三部分存储 峰顶 峰脊 峰谷 峰脊一个周期（上山下山）会出现两次
     */
    public static String convert(String source, int numRows){

        if (source==null || numRows <=1 || source.length()<=numRows){
            return source;
        }

        int len = source.length();
        char[] chars = source.toCharArray();

        int cycleNum = 2*(numRows-1);
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<len; i+=cycleNum){ //存储峰顶
            sb.append(chars[i]);
        }

        /*int step = numRows-2; //只保存了第二行
        for (int i=1; i<len; i+=step){
            sb.append(chars[i]);
            step = cycleNum -step;
        }*/
        for (int l=1; l<numRows-1; l++){ //逐行保存峰脊
            int step = 2*l;
            for (int i=l; i<len; i+=step){
                sb.append(chars[i]);
                step = cycleNum - step;
            }
        }

        for (int i=numRows-1; i<len; i+=cycleNum){ //存储峰谷
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    public static String convert2(String source, int numRows){

        if (source==null || numRows <=1 || source.length()<=numRows){
            return source;
        }

        int len = source.length();
        char[] chars = source.toCharArray();
        StringBuilder[] sbs = new StringBuilder[numRows];
        StringBuilder sbRes = new StringBuilder();

        for (int i=0; i<numRows; i++){
            sbs[i] = new StringBuilder();
        }

        int i=0;
        while (i<len){
            for (int j=0; j<numRows && i<len; j++){ //保存竖列数据，i<len为了不越界
                sbs[j].append(chars[i++]);
            }

            for (int j=numRows-2; j>=1 && i<len; j--){ //保存斜线数据
                sbs[j].append(chars[i++]);
            }
        }

        for (StringBuilder sb: sbs){
            System.out.println(sb.toString());
            sbRes.append(sb.toString());
        }
        return sbRes.toString();
    }
}
