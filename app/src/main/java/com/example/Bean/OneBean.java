package com.example.Bean;

import java.util.List;

/**
 * Created by 文龙 on 2020/1/8.
 */

public class OneBean {
    /**
     * data : [{"collect_num":"1669","food_str":"大虾 葱 生姜 植物油 料酒","id":"8289","num":1669,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/9/8289.jpg","title":"油焖大虾"},{"collect_num":"1591","food_str":"猪肉 青蒜 青椒 红椒 姜片","id":"2127","num":1591,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2127.jpg","title":"四川回锅肉"},{"collect_num":"1544","food_str":"QQ糖 牛奶 芒果","id":"30630","num":1544,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/31/30630.jpg","title":"超简单芒果布丁"},{"collect_num":"1425","food_str":"鲜鱼 姜 葱 蒜 花椒","id":"9073","num":1425,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/10/9073.jpg","title":"家常红烧鱼"},{"collect_num":"1419","food_str":"豆腐 新鲜红椒 青椒 葱花 油","id":"10097","num":1419,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10097.jpg","title":"家常煎豆腐"},{"collect_num":"1342","food_str":"瘦猪肉 生菜 豆瓣酱 干辣椒 花椒","id":"10509","num":1342,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10509.jpg","title":"水煮肉片"},{"collect_num":"1252","food_str":"银耳 苹果 红糖","id":"46968","num":1252,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/47/46968.jpg","title":"红糖苹果银耳汤"},{"collect_num":"1222","food_str":"豆腐 肉末 生抽 白糖 芝麻油","id":"10191","num":1222,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10191.jpg","title":"麻婆豆腐"},{"collect_num":"1151","food_str":"大米 皮蛋 猪肉 油条 香葱","id":"2372","num":1151,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2372.jpg","title":"皮蛋瘦肉粥"},{"collect_num":"1144","food_str":"红薯粉 肉 姜 蒜 花椒","id":"2166","num":1144,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2166.jpg","title":"蚂蚁上树"},{"collect_num":"1078","food_str":"猪肉 红椒 黄椒 洋葱 蛋清","id":"2262","num":1078,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2262.jpg","title":"糖醋肉"},{"collect_num":"1010","food_str":"豆腐 木耳 胡萝卜 香葱 番茄酱","id":"9971","num":1010,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/10/9971.jpg","title":"鱼香豆腐"},{"collect_num":"993","food_str":"四季豆 干辣椒 蒜头 酱油 糖","id":"10172","num":993,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10172.jpg","title":"干煸四季豆"},{"collect_num":"927","food_str":"胡萝卜 肉 蛋 生抽 盐","id":"2685","num":927,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2685.jpg","title":"胡萝卜肉末蒸蛋"},{"collect_num":"892","food_str":"青辣椒 大蒜 香醋 白糖 生抽","id":"9972","num":892,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/10/9972.jpg","title":"虎皮青椒"},{"collect_num":"803","food_str":"排骨 李锦记叉烧酱 植物油 清水 油菜","id":"10437","num":803,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10437.jpg","title":"叉烧排骨"},{"collect_num":"760","food_str":"黑木耳 玉米 牛蒡 胡萝卜 西兰花","id":"2892","num":760,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2892.jpg","title":"\u201c五行\u201d彩蔬汤"},{"collect_num":"758","food_str":"面条 肉丝 淀粉 酱油 辣椒","id":"2348","num":758,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2348.jpg","title":"麻辣肉丝面"},{"collect_num":"757","food_str":"黄豆 红豆 绿豆 黑豆 黑米","id":"33783","num":757,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/34/33783.jpg","title":"美人豆浆"},{"collect_num":"756","food_str":"土豆 翅根 葱 姜 料酒","id":"10044","num":756,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10044.jpg","title":"土豆炖翅根"}]
     * ret : 1
     */

    private int ret;
    private List<DataBean> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * collect_num : 1669
         * food_str : 大虾 葱 生姜 植物油 料酒
         * id : 8289
         * num : 1669
         * pic : http://www.qubaobei.com/ios/cf/uploadfile/132/9/8289.jpg
         * title : 油焖大虾
         */

        private String collect_num;
        private String food_str;
        private String id;
        private int num;
        private String pic;
        private String title;

        public String getCollect_num() {
            return collect_num;
        }

        public DataBean(String food_str, String title) {
            this.food_str = food_str;
            this.title = title;
        }

        public void setCollect_num(String collect_num) {
            this.collect_num = collect_num;
        }

        public String getFood_str() {
            return food_str;
        }

        public void setFood_str(String food_str) {
            this.food_str = food_str;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
