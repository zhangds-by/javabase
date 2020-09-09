package com.zhangds.java8;

import com.zhangds.common.model.Hero;
import com.zhangds.common.model.HeroVo;
import com.zhangds.common.util.BigDecimalUtils;
import com.zhangds.common.util.StreamUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Stream {

    public static void main(String[] args) {

        List<Hero> list = new ArrayList<>();
        list.add(new Hero(1L, "亚瑟", 120, 0, "战士", "男", new BigDecimal(1200.00)));
        list.add(new Hero(2L, "妲己", 121, 121, "法师", "女", new BigDecimal(1200.00)));
        list.add(new Hero(3L, "橘子", 122, 0, "战士", "男", new BigDecimal(1200.00)));
        list.add(new Hero(4L, "周瑜", 123, 122, "法师", "男", new BigDecimal(1200.00)));
        list.add(new Hero(4L, "小乔", 124, 123, "法师", "女", new BigDecimal(1200.00)));

        //分组
        Map<String, List<Hero>> groupMap = list.stream().collect(Collectors.groupingBy(Hero::getSex));
        System.out.println("分组结果" + groupMap);

        //把组合键封装为 数组 进行分组
        Function<Hero, List<Object>> compositeKey = t -> Arrays.<Object>asList(t.getSex(), t.getLocation());
        Map<Object, List<Hero>> multiGroupMap = list.stream().collect(Collectors.groupingBy(compositeKey, Collectors.toList()));
        System.out.println("组合键分组结果" + multiGroupMap);

        //过滤
        Hero filterRes = list.stream().filter(t -> t.getLocation().equals("法师")).findAny().orElse(null);
        List<Hero> resList = list.stream().filter(t -> t.getLocation().equals("法师")).collect(Collectors.toList());

        //求和
        double hpSum = list.stream().mapToDouble(Hero::getHp).sum();
        System.out.println("求和结果" + hpSum);

        list.stream().map(Hero::getHp).reduce(0d, Double::sum);
        list.stream().map(Hero::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add); //不能处理null, a = a.add(b);
        list.stream().map(Hero::getPrice).reduce(BigDecimal.ZERO, BigDecimalUtils::sum); //重写累加方法，处理null

        //最大最小值
        Double mapMax = list.stream().map(Hero::getMp).max(Double::compareTo).get();
        Hero maxObject = list.stream().max(Comparator.comparing(Hero::getHp)).get();
        System.out.println("最大值结果" + mapMax + maxObject.getId());

        //平均数
        double average = list.stream().mapToDouble(Hero::getHp).average().getAsDouble();

        //List 转map
        list.stream().collect(Collectors.toMap(Hero::getId, a->a, (t1,t2)->t1)); //有重复的id，舍弃t2使用t1

        //多字段排序
        list.sort(Comparator.comparing(Hero::getId).thenComparing(Hero::getPrice));
        List<Hero> sortList = list.stream().sorted(Comparator.comparing(Hero::getId).reversed()).limit(2).collect(Collectors.toList());

        //去重
        list.stream().distinct().collect(Collectors.toList());
        //按属性去重
        List<Hero> distinctList = list.stream().filter(StreamUtils.distinctByKey(t->t.getId())).collect(Collectors.toList());
        List<Hero> uniqueList = list.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingLong(Hero::getId))), ArrayList::new)
        );
//        System.out.println("去重后的列表" + uniqueList);

        //获取list对象的某个字段组装成新list
        List<Long> userIdList = list.stream().map(a -> a.getId()).collect(Collectors.toList());
        System.out.println("按某个字段重新组装的list" + userIdList);

        //不同实体list的拷贝
        List<HeroVo> copyList = list.stream().map(p -> {
            HeroVo e = new HeroVo();
            e.setHeroName(p.getName());
            e.setHeroSex(p.getSex());
            return e;
        }).collect(Collectors.toList());
        System.out.println("复制后的对象列表" + copyList);

    }
}
