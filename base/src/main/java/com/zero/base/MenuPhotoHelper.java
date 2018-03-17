package com.zero.base;


import java.util.HashMap;

/**
 * Created by zk
 */

public class MenuPhotoHelper {
    public static final String ONE_M_ROUBAO = "one_m_roubao";
    public static final String ONE_N_JITUI = "one_n_jitui";
    public static final String ONE_NN_XIHONGSHI = "one_nn_xihongshi";

    public static final String TWO_M_ROUBAO = "two_m_roubao";
    public static final String TWO_N_CHAOFAN = "two_n_chaofan";
    public static final String TWO_NN_TUDOUSI = "two_nn_tudousi";

    public static final String NIU_ROU_CHAO_FAN = "niu_rou_chao_fan";

    public static final String chayedan = "chayedan";
    public static final String doujiang = "doujiang";
    public static final String duojiaoyutou = "duojiaoyutou";
    public static final String hundun = "hundun";
    public static final String mapodoufu = "mapodoufu";
    public static final String matuan = "matuan";
    public static final String mifen = "mifen";
    public static final String paigutang = "paigutang";
    public static final String shaoqiezi = "shaoqiezi";

    public static final String youtiao = "youtiao";
    public static final String zhimabing = "zhimabing";
    public static final String zhou = "zhou";

    public static final String jiaozi = "jiaozi";

    public static final HashMap<String, Integer> IMG = new HashMap<>();

    static {
        IMG.put(ONE_N_JITUI, R.drawable.one_n_jitui);
        IMG.put(ONE_NN_XIHONGSHI, R.drawable.one_nn_xihongshi);
        IMG.put(ONE_M_ROUBAO, R.drawable.one_m_roubao);

        IMG.put(TWO_M_ROUBAO, R.drawable.one_m_roubao);
        IMG.put(TWO_N_CHAOFAN, R.drawable.two_n_chaofan);
        IMG.put(TWO_NN_TUDOUSI, R.drawable.two_nn_tudousi);

        IMG.put(NIU_ROU_CHAO_FAN, R.drawable.four_nn_chaofan_niurou);

        IMG.put(chayedan, R.drawable.chayedan);
        IMG.put(doujiang, R.drawable.doujiang);
        IMG.put(duojiaoyutou, R.drawable.duojiaoyutou);
        IMG.put(hundun, R.drawable.hundun);
        IMG.put(mapodoufu, R.drawable.mapodoufu);
        IMG.put(matuan, R.drawable.matuan);
        IMG.put(mifen, R.drawable.mifen);
        IMG.put(paigutang, R.drawable.paigutang);
        IMG.put(shaoqiezi, R.drawable.shaoqiezi);
        IMG.put(youtiao, R.drawable.youtiao);
        IMG.put(zhimabing, R.drawable.zhimabing);
        IMG.put(zhou, R.drawable.zhou);

        IMG.put(jiaozi, R.drawable.jiaozi);

    }

}
