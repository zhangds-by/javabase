package com.zhangds.arth.leetcode;

/**
 * 's'是否正则匹配'p'
 *
 * aa/a*    true
 * aa/a     false
 * ab/.*    true
 * aab/c*a*b*   true
 * aaibbi/a*b*  false
 *
 * 1、s和p都是空             true
 * 2、长度为1，首位相同       true
 * 3、以正则表达式 p 为主判断：
 *      p 第二个字符不为 '*'    s 为空     false
 *                                     s 不为空    首位匹配
 *      p 第二个字符为'*'  s不为空  首位匹配   递归匹配 s 和 去除前两位的 p，同时 s去除匹配成功的字符
 *
 * 4、匹配 0 个 则 去除 p 前两位，继续和 s 匹配
 */
public class L0010RegularExpressionMatch {

    public static void main(String[] args) {

        System.out.println(isRegExpMatch("aab", "c*a*b*"));
        System.out.println(isRegExpMatch("aaibbi", "a*b*"));
        System.out.println(isRegExpMatch("ab", ".*"));
        System.out.println(isRegExpMatch("abcac", ".*.*"));
        System.out.println(isRegExpMatch("mississippi", "mis*is*p*."));

        System.out.println(isRegExpMatch2("aab", "c*a*b*"));
        System.out.println(isRegExpMatch2("aaibbi", "a*b*"));
        System.out.println(isRegExpMatch2("ab", ".*"));
        System.out.println(isRegExpMatch2("abcac", ".*.*"));
        System.out.println(isRegExpMatch2("mississippi", "mis*is*p*."));

        System.out.println(isRegExpMatch3("aab", "c*a*b*"));
        System.out.println(isRegExpMatch3("aaibbi", "a*b*"));
        System.out.println(isRegExpMatch3("ab", ".*"));
        System.out.println(isRegExpMatch3("abcac", ".*.*"));
        System.out.println(isRegExpMatch3("mississippi", "mis*is*p*."));

        System.out.println(isMatch("aab", "c*a*b*"));
        System.out.println(isMatch("aaibbi", "a*b*"));
        System.out.println(isMatch("ab", ".*"));
        System.out.println(isMatch("abcac", ".*.*"));
        System.out.println(isMatch("mississippi", "mis*is*p*."));

    }

    /**
     * 通过分析 p 的格式
     */
    public static boolean isRegExpMatch(String s, String p){

        //不能使用数组操作
//        char[] schars = s.toCharArray();
//        char[] pchars = p.toCharArray();

        if (p.isEmpty()) return s.isEmpty();

        if (p.length()==1){
            return s.length() == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        }

        if (p.charAt(1) != '*'){
            if (s.isEmpty()) return false;
//            return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
//                    && isRegExpMatch(s.substring(1), p.substring(1)); //首位匹配成功 并 去掉首位字符
            if (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'){
                s = s.substring(1);
                p = p.substring(1);
            }else {
                return false;
            }
        }

        // p[1] == '*' 且匹配一个多个字符
        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')){ //递归头
            if (isRegExpMatch(s, p.substring(2))) return true;
            s = s.substring(1);
        }

        //匹配0位
        return isRegExpMatch(s, p.substring(2));
    }

    public static boolean isRegExpMatch2(String s, String p){

        if (p.isEmpty()) return s.isEmpty();

        /**
         * p 的第二个字符为 '*' , s 不为空，递归调用个数为0的情况
         *                              首字符匹配，去除 s 的首字符继续对 原p 做匹配
         */
        if (p.length()>1 && p.charAt(1) == '*'){
            return isRegExpMatch2(s, p.substring(2))
                    || (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'))
                    && isRegExpMatch2(s.substring(1), p);
        }

        /**
         * p 的第二个字符不为 '*' 匹配首字符
         */
        return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
                && isRegExpMatch2(s.substring(1), p.substring(1));
    }

    /**
     * TODO: 动态规划法： 定义 'dp[i][j]' 的真假来表示 's[0..i)' 是否匹配 'p[0..j)'
     *
     * 1、p[j - 1] == '*'
     *
     * 2、p[j - 1] != '*'
     *
     * dab
     * dc*a*b*
     */
    public static boolean isRegExpMatch3(String s, String p){
        if (p.length() == 0) return s.length() == 0;
        int sL = s.length();
        int pL = p.length();
        boolean[][] dp = new boolean[sL + 1][pL + 1];
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();

        for (int i = 2; i <= pL; ++i) { // 连续的 *
            if (pc[i - 1] == '*' && dp[0][i - 2]) {
                dp[0][i] = true;
            }
        }

        dp[0][0] = true;

        for (int i=1; i<=sL; ++i){
            for (int j=1; j<=pL; ++j){
                if (pc[j - 1] == '*'){ // 有两种情况 匹配0个，匹配多个
                    dp[i][j] = dp[i][j-2] || (sc[i-1] == pc[j-2] || pc[j-2] == '.') && dp[i-1][j];
                }else {
                    dp[i][j] = (sc[i-1] == pc[j-1] || pc[j-1] == '.') && dp[i-1][j-1];
                }
            }
        }

//        for (int i=0; i<=sL; i++){
//            for (int j=0; j<=pL; j++){
//                System.out.print(dp[i][j] == true ? 1 + " " : 0 + " ");
//            }
//            System.out.println();
//        }

        return dp[sL][pL];
    }

    public static boolean isMatch(String s, String p) {
        if (p.length() == 0) return s.length() == 0;
        int sL = s.length(), pL = p.length();
        boolean[][] dp = new boolean[sL + 1][pL + 1];
        char[] sc = s.toCharArray(), pc = p.toCharArray();
        dp[0][0] = true;
        for (int i = 2; i <= pL; ++i) { // 连续的 *
            if (pc[i - 1] == '*' && dp[0][i - 2]) {
                dp[0][i] = true;
            }
        }
        for (int i = 1; i <= sL; ++i) {
            for (int j = 1; j <= pL; ++j) {
                if (pc[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 2] || (pc[j - 2] == sc[i - 1] || pc[j - 2] == '.') && dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && (pc[j - 1] == '.' || pc[j - 1] == sc[i - 1]);
                }
            }
        }
        return dp[sL][pL];
    }
}
