package com.zhangds.java8.demo.smallcase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangds.java8.demo.entities.StudentScore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用jackson包需要将core包下的所有包导入
 * @author: zhangds
 * @date: 2021/3/9 9:11
 */
public class MergeDemo {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        /*List<StudentScore> list = buildATestList();
        Map<String, Integer> map = new HashMap<>();
        list.forEach(score -> {
            if (map.containsKey(score.getStuName())){
                map.put(score.getStuName(), map.get(score.getStuName())+score.getScore());
            }else {
                map.put(score.getStuName(), score.getScore());
            }
        });
        try {
            System.out.println(objectMapper.writeValueAsString(map));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
        Map<String, Integer> map = new HashMap<>();
        List<StudentScore> list = buildATestList();
        list.forEach(score -> map.merge(
                score.getStuName(),
                score.getScore(),
                Integer::sum
        ));
        try {
            System.out.println(objectMapper.writeValueAsString(map));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static List<StudentScore> buildATestList(){
        List<StudentScore> studentScoreList = new ArrayList<>();
        StudentScore studentScore1 = new StudentScore() {{
            setStuName("张三");
            setSubject("语文");
            setScore(70);
        }};
        StudentScore studentScore2 = new StudentScore() {{
            setStuName("张三");
            setSubject("数学");
            setScore(80);
        }};
        StudentScore studentScore3 = new StudentScore() {{
            setStuName("张三");
            setSubject("英语");
            setScore(65);
        }};
        StudentScore studentScore4 = new StudentScore() {{
            setStuName("李四");
            setSubject("语文");
            setScore(68);
        }};
        StudentScore studentScore5 = new StudentScore() {{
            setStuName("李四");
            setSubject("数学");
            setScore(70);
        }};
        StudentScore studentScore6 = new StudentScore() {{
            setStuName("李四");
            setSubject("英语");
            setScore(90);
        }};
        StudentScore studentScore7 = new StudentScore() {{
            setStuName("王五");
            setSubject("语文");
            setScore(80);
        }};
        StudentScore studentScore8 = new StudentScore() {{
            setStuName("王五");
            setSubject("数学");
            setScore(85);
        }};
        StudentScore studentScore9 = new StudentScore() {{
            setStuName("王五");
            setSubject("英语");
            setScore(70);
        }};

        studentScoreList.add(studentScore1);
        studentScoreList.add(studentScore2);
        studentScoreList.add(studentScore3);
        studentScoreList.add(studentScore4);
        studentScoreList.add(studentScore5);
        studentScoreList.add(studentScore6);
        studentScoreList.add(studentScore7);
        studentScoreList.add(studentScore8);
        studentScoreList.add(studentScore9);

        return studentScoreList;
    }
}
