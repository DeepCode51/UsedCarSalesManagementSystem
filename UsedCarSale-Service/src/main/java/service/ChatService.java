package service;

import com.pojo.Chat;
import common.Assist;

import java.util.List;

/**
 * @author bahjan123
 * @date 2021/5/8 16:57
 */
public interface ChatService {



    List<Chat> selectChat(String empId);

    void insert(Chat c);
}
