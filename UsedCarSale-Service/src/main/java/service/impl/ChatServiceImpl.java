package service.impl;

import com.dao.ChatMapper;
import com.pojo.Chat;
import common.Assist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ChatService;

import java.util.List;

/**
 * @author bahjan123
 * @date 2021/5/8 16:57
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMapper chatMapper;

    @Override
    public List<Chat> selectChat(String empId) {

        return chatMapper.selectChat(empId);
    }

    @Override
    public void insert(Chat c) {
        chatMapper.insert(c);
    }
}
