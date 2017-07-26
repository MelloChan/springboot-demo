package com.mello.service;

import com.mello.domain.Question;

/**
 * Created by Administrator on 2017/5/20.
 * 疑问实体类服务接口
 */
public interface QuestionService {
    /**
     * 保存疑问
     * @param question 疑问实体类
     * @return 成功与否
     */
    Boolean saveQuestion(Question question);
}
