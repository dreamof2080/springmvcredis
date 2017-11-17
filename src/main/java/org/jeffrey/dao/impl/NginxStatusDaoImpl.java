package org.jeffrey.dao.impl;


import com.alibaba.fastjson.JSONObject;
import org.jeffrey.base.BaseRedisGeneratorDao;
import org.jeffrey.dao.NginxStatusDao;
import org.jeffrey.model.NginxStatus;
import org.jeffrey.util.KeyUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * nginxstatusDao实现类
 * @author Jeffrey
 */
@Repository(value = "nginxStatusDao")
public class NginxStatusDaoImpl extends BaseRedisGeneratorDao<String,Integer> implements NginxStatusDao {
    @Override
    public void add(final NginxStatus nginxStatus) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(KeyUtils.nginxStatus(nginxStatus.getDateTime()),nginxStatus.getActiveConnections()+"");
    }

    @Override
    public JSONObject getNginxStatusList(String beginDate,String endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar= Calendar.getInstance();
        boolean flag = true;
        String date_tmp = "";
        JSONObject jsonObject = new JSONObject();
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        List<String> keyList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
        try {
            if(simpleDateFormat.parse(beginDate).getTime()>simpleDateFormat.parse(endDate).getTime()){
                return null;
            }
            calendar.setTime(simpleDateFormat.parse(beginDate));
            while(flag){
                date_tmp = simpleDateFormat.format(calendar.getTime());
                String keyPattern = KeyUtils.nginxStatus(date_tmp)+"*";
                Set<String> keySet = stringRedisTemplate.keys(keyPattern);
                JSONObject jsonObject_tmp = conversionData(keySet);
                Set<String> newKeySet = (Set<String>)jsonObject_tmp.get("keySet");
                List<String> keyList_tmp = (List<String>)jsonObject_tmp.get("keyList");
                List<String> valueList_tmp = valueOperations.multiGet(newKeySet);
                keyList.addAll(keyList_tmp);
                valueList.addAll(valueList_tmp);

                if(date_tmp.equals(endDate)){
                    flag = false;
                }else{
                    calendar.add(Calendar.DATE,1);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        jsonObject.put("timeData",keyList);
        jsonObject.put("lineData",valueList);
        return jsonObject;
    }

    /**
     * 把无序的set转换成有序的set，并替换掉标识符生成一个新的list
     * @param keySet
     * @return keyList:替换标识符后的有序的key;keySet：有序的set集合
     */
    private JSONObject conversionData(Set<String> keySet){
        JSONObject jsonObject = new JSONObject();
        List<String> keyList = new ArrayList<>(keySet);
        Collections.sort(keyList);
        Set<String> newKeySet = new TreeSet<>(keyList);

        for(int i=0;i<keyList.size();i++){
            keyList.set(i,keyList.get(i).replace(KeyUtils.nginxStatus(""),""));
        }

        jsonObject.put("keyList",keyList);
        jsonObject.put("keySet",newKeySet);
        return jsonObject;
    }

}
