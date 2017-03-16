package iamfqq.d3assistant;

import java.io.Serializable;

/**
 * Created by JUQIANG-PC on 2017/3/16.
 */

public class A implements Serializable{
        public static final int TYPE_CHECKED = 1;
        public static final int TYPE_NOCHECKED = 0;

        LeaderBoard board;
        int type;

        public A(LeaderBoard board, int type) {
            this.board = board;
            this.type = type;
        }
}
