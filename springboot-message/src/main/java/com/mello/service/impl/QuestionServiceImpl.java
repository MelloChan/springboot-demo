package com.mello.service.impl;

import com.mello.dao.QuestionDAO;
import com.mello.domain.Question;
import com.mello.service.QuestionService;
import com.mello.util.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;

/**
 * Created by Administrator on 2017/5/20.
 * 疑问实体类服务实现
 * */
@Service
public class QuestionServiceImpl implements QuestionService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final QuestionDAO questionDAO;
    @Autowired
    public QuestionServiceImpl(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @Override
    public Boolean saveQuestion(Question question) {
        new Thread(() -> {
            try {
                SendEmail.sendMailByText(1,question.toString());
            } catch (GeneralSecurityException | MessagingException e) {
                logger.error(e.getMessage());
            }
        }).start();
        return questionDAO.saveQuestion(question);
    }
}
