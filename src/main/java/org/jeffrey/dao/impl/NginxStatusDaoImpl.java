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
import java.util.Calendar;
import java.util.List;
import java.util.Set;

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
        try {
            if(simpleDateFormat.parse(beginDate).getTime()>simpleDateFormat.parse(endDate).getTime()){
                return null;
            }
            calendar.setTime(simpleDateFormat.parse(beginDate));
            while(flag){
                date_tmp = simpleDateFormat.format(calendar.getTime());
                String keyPattern = KeyUtils.nginxStatus(date_tmp)+"*";
                Set<String> keySet = stringRedisTemplate.keys(keyPattern);
                List<String> valueList = valueOperations.multiGet(keySet);
                jsonObject.put("timeData",keySet);
                jsonObject.put("lineData",valueList);
                if(date_tmp.equals(endDate)){
                    flag = false;
                }else{
                    calendar.add(Calendar.DATE,1);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
