package com.dao;

import com.pojo.Carorder;
import com.pojo.Chat;
import common.Assist;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatMapper {


	List<Chat> selectChat(String empId);

    void insert(Chat c);
}