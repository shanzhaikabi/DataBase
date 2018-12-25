package com.ssh.service;

import com.ssh.entity.Clazz;

import java.util.List;

public interface ClassService {
    List<Clazz> ShowClassByName(String name);
    String GetNameById(String id);
}
