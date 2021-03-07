package sort;

/**
 * @author WeiJinglun
 * @date 2021.03.05
 */
public class Solution1 {

    //a~z,  	   a~z .（单个任务字符） *（前单侧字符人资多次）
    //
    //boolean
    //
    //aaa 	a.a  		true
    //		ab*ac*a  	true
    //		ab*a 		false

    // dist = int27[0 没有, n 单个次数, -1 任意次,  0-n ]

    public boolean process(String s, String p) {
        if (s.length()==0) {
            return false;
        }

        int[] dist = new int[27];
        char[] chars = p.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            char c = chars[i];
            switch (c) {
                case '.':
                    dist[26] += 1;
                    break;
                case '*':
                    char e = chars[--i];
                    if (e == '.') {
                        return true;
                    }
                    dist[e - 'a'] = -1;
                    break;
                default:
                    int count = dist[c - 'a'];
                    if (count != -1) {
                        dist[c - 'a'] = count + 1;
                    }
                    break;
            }
        }

        // 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            // 当前来到的字符
            char c = s.charAt(i);
            int count = dist[c - 'a'];

            if (count == -1) {
                // 继续
            } else if (count == 0) {
                // 去查看 任意字符位
                count = dist[26];
                // 任意字符位置没有可用
                if (count <= 0) {
                    return false;
                } else {
                    // 修改
                    dist[26] = count - 1;
                }
            } else {
                dist[c - 'a'] = count - 1;
            }
        }


        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().process("abcab", "ab*a.*"));
    }
}
