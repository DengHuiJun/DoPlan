package com.zero.base;


import java.util.HashMap;

/**
 * Created by zerogeek on 2018/3/14.
 */

public class MenuPhotoHelper {
    public static final String ONE_M_ROUBAO = "one_m_roubao";
    public static final String ONE_N_JITUI = "one_n_jitui";
    public static final String ONE_NN_XIHONGSHI = "one_nn_xihongshi";

    public static final String TWO_M_ROUBAO = "two_m_roubao";
    public static final String TWO_N_CHAOFAN = "two_n_chaofan";
    public static final String TWO_NN_TUDOUSI = "two_nn_tudousi";

    public static final String NIU_ROU_CHAO_FAN = "niu_rou_chao_fan";

    public static final HashMap<String, Integer> IMG = new HashMap<>();

    static {
        IMG.put(ONE_M_ROUBAO, R.drawable.one_m_roubao);
        IMG.put(ONE_N_JITUI, R.drawable.one_m_roubao);
        IMG.put(ONE_NN_XIHONGSHI, R.drawable.one_m_roubao);

        IMG.put(TWO_M_ROUBAO, R.drawable.one_m_roubao);
        IMG.put(TWO_N_CHAOFAN, R.drawable.two_n_chaofan);
        IMG.put(TWO_NN_TUDOUSI, R.drawable.two_nn_tudousi);

        IMG.put(NIU_ROU_CHAO_FAN, R.drawable.four_nn_chaofan_niurou);
    }

}
